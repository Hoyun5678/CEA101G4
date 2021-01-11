package com.room.model;

import java.util.*;
import java.util.stream.Collectors;

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Room;
import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Sell;
import util.Util;

import java.sql.*;
import java.sql.Date;

public class RoomJDBCDAO implements RoomDAO_interface {

	private static final String INSERT_STMT = 
			"INSERT INTO ROOM_PRODUCT(ROOM_ID, SELL_MEM_ID, ROOM_NAME, ROOM_PRICE, ROOM_CAPACITY, ROOM_DES, ROOM_STATUS, ROOM_ON_TIME)"
				+" VALUES ('ROOM' || LPAD(ROOM_SEQ.NEXTVAL, 3, '0'), ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
//		"INSERT INTO ROOM_PRODUCT(ROOM_ID, SELL_MEM_ID, ROOM_NAME, ROOM_PRICE, ROOM_CAPACITY, ROOM_ON_TIME, ROOM_DES, ROOM_COLLECT, ROOM_STATUS)"
//		+" VALUES ('ROOM' || LPAD(ROOM_SEQ.NEXTVAL, 3, '0'), ?, ?, ?, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM ROOM_PRODUCT ORDER BY ROOM_ID DESC";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM ROOM_PRODUCT WHERE ROOM_ID = ?";
		private static final String DELETE = 
			"DELETE FROM ROOM_PRODUCT WHERE ROOM_ID = ?";
		private static final String UPDATE = 
			"UPDATE ROOM_PRODUCT SET SELL_MEM_ID=?, ROOM_NAME=?, ROOM_PRICE=?, ROOM_CAPACITY=?, ROOM_ON_TIME=?, ROOM_DES=?, ROOM_COLLECT=?, ROOM_STATUS=? WHERE ROOM_ID = ?";
		private static final String GET_BYMEMID_STMT = 
			"SELECT * FROM ROOM_PRODUCT WHERE SELL_MEM_ID = ? ORDER BY ROOM_STATUS DESC, ROOM_ID DESC";
		private static final String GET_BYDATERANGE_STMT = 
			"SELECT ROOM_ID FROM ROOM_ORDERED_DATE_RECORD " + 
			"WHERE TO_DATE(?, 'yyyy-MM-dd') in ROOM_ORDERED_DATE";
	
	@Override
	public RoomVO insert(RoomVO roomVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			
			String[] cols = new String[] {"ROOM_ID"};
			con.setAutoCommit(false);
						
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			
			pstmt.setString(1, roomVO.getSellMemId());
			pstmt.setString(2, roomVO.getRoomName());
			pstmt.setInt(3, roomVO.getRoomPrice());
			pstmt.setInt(4, roomVO.getRoomCapacity());
			pstmt.setString(5, roomVO.getRoomDes());
			pstmt.setInt(6, roomVO.getRoomStatus());

			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			String nextKey = "";
			while(rs.next()) {
				nextKey = rs.getString(1);
				roomVO.setRoomId(nextKey);
			}
			con.commit();
			con.setAutoCommit(true);
			return roomVO;
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(RoomVO roomVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, roomVO.getSellMemId());
			pstmt.setString(2, roomVO.getRoomName());
			pstmt.setInt(3, roomVO.getRoomPrice());
			pstmt.setInt(4, roomVO.getRoomCapacity());
			pstmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			pstmt.setString(6, roomVO.getRoomDes());
			pstmt.setInt(7, roomVO.getRoomCollect());
			pstmt.setInt(8, roomVO.getRoomStatus());
			pstmt.setString(9, roomVO.getRoomId());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(String roomId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, roomId);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public RoomVO findByPrimaryKey(String roomId) {

		RoomVO roomVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, roomId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomVO = new RoomVO();
				roomVO.setRoomId(rs.getString("ROOM_ID"));
				roomVO.setSellMemId(rs.getString("SELL_MEM_ID"));
				roomVO.setRoomName(rs.getString("ROOM_NAME"));
				roomVO.setRoomPrice(rs.getInt("ROOM_PRICE"));
				roomVO.setRoomCapacity(rs.getInt("ROOM_CAPACITY"));
				roomVO.setRoomOnTime(rs.getTimestamp("ROOM_ON_TIME"));
				roomVO.setRoomDes(rs.getString("ROOM_DES"));
				roomVO.setRoomCollect(rs.getInt("ROOM_COLLECT"));
				roomVO.setRoomStatus(rs.getInt("ROOM_STATUS"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return roomVO;
	}

	@Override
	public List<RoomVO> getAll() {
		List<RoomVO> list = new ArrayList<RoomVO>();
		RoomVO roomVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomVO = new RoomVO();
				roomVO.setRoomId(rs.getString("ROOM_ID"));
				roomVO.setSellMemId(rs.getString("SELL_MEM_ID"));
				roomVO.setRoomName(rs.getString("ROOM_NAME"));
				roomVO.setRoomPrice(rs.getInt("ROOM_PRICE"));
				roomVO.setRoomCapacity(rs.getInt("ROOM_CAPACITY"));
				roomVO.setRoomOnTime(rs.getTimestamp("ROOM_ON_TIME"));
				roomVO.setRoomDes(rs.getString("ROOM_DES"));
				roomVO.setRoomCollect(rs.getInt("ROOM_COLLECT"));
				roomVO.setRoomStatus(rs.getInt("ROOM_STATUS"));
				list.add(roomVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	
	@Override
	public List<RoomVO> getAll(Map<String, String[]> map) {
		List<RoomVO> list = new ArrayList<RoomVO>();
		RoomVO roomVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			String finalSQL = "SELECT * FROM ROOM_PRODUCT LEFT JOIN SELLER_MEMBER ON ROOM_PRODUCT.SELL_MEM_ID = SELLER_MEMBER.SELL_MEM_ID "
					+ jdbcUtil_CompositeQuery_Room.get_WhereCondition(map);
			
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("finalSQL = " + finalSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomVO = new RoomVO();
				roomVO.setRoomId(rs.getString("ROOM_ID"));
				roomVO.setSellMemId(rs.getString("SELL_MEM_ID"));
				roomVO.setRoomName(rs.getString("ROOM_NAME"));
				roomVO.setRoomPrice(rs.getInt("ROOM_PRICE"));
				roomVO.setRoomCapacity(rs.getInt("ROOM_CAPACITY"));
				roomVO.setRoomOnTime(rs.getTimestamp("ROOM_ON_TIME"));
				roomVO.setRoomDes(rs.getString("ROOM_DES"));
				roomVO.setRoomCollect(rs.getInt("ROOM_COLLECT"));
				roomVO.setRoomStatus(rs.getInt("ROOM_STATUS"));
				list.add(roomVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	
	@Override
	public List<RoomVO> getByDateRange(Date from, Date to) {
		RoomService roomSvc = new RoomService();
		List<RoomVO> list = roomSvc.getAll();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {			
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			List<String> listOrderedRoomId = new ArrayList<String>();
			
			long fromToLong = from.getTime();
			do {
				pstmt = con.prepareStatement(GET_BYDATERANGE_STMT);
				pstmt.setString(1, new Date(fromToLong).toString());
				rs = pstmt.executeQuery();
				while (rs.next()) {
					listOrderedRoomId.add(rs.getString(1));
				}
				fromToLong += 24 * 60 * 60 * 1000L;
			} while(fromToLong < to.getTime());
//			System.out.println("not qualified roomVO : " + listOrderedRoomId);
			
			// return qulified roomVO List
			list = (List<RoomVO>) list.stream()
					.filter(e -> !listOrderedRoomId.contains(e.getRoomId()))
					.collect(Collectors.toList());
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	
	@Override
	public List<RoomVO> getByMemId(String sellMemId) {
		List<RoomVO> list = new ArrayList<RoomVO>();
		RoomVO roomVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_BYMEMID_STMT);
			
			pstmt.setString(1, sellMemId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomVO = new RoomVO();
				roomVO.setRoomId(rs.getString("ROOM_ID"));
				roomVO.setSellMemId(rs.getString("SELL_MEM_ID"));
				roomVO.setRoomName(rs.getString("ROOM_NAME"));
				roomVO.setRoomPrice(rs.getInt("ROOM_PRICE"));
				roomVO.setRoomCapacity(rs.getInt("ROOM_CAPACITY"));
				roomVO.setRoomOnTime(rs.getTimestamp("ROOM_ON_TIME"));
				roomVO.setRoomDes(rs.getString("ROOM_DES"));
				roomVO.setRoomCollect(rs.getInt("ROOM_COLLECT"));
				roomVO.setRoomStatus(rs.getInt("ROOM_STATUS"));
				list.add(roomVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	
	
	public static void main(String[] args) {

		RoomJDBCDAO dao = new RoomJDBCDAO();
		
		
		
		
//
//		// 新增
//		RoomVO roomVO1 = new RoomVO();
//		roomVO1.setSellMemId("SELL002");
//		roomVO1.setRoomName("testRoomName");
//		roomVO1.setRoomPrice(3000);
//		roomVO1.setRoomCapacity(4);
//		roomVO1.setRoomOnTime(new Timestamp(System.currentTimeMillis()));
//		roomVO1.setRoomDes("好山好水好無聊的漂亮民宿");
//		roomVO1.setRoomCollect(77);
//		roomVO1.setRoomStatus(0);
//		dao.insert(roomVO1);
//
//		// 修改
//		RoomVO roomVO2 = new RoomVO();
//		roomVO2.setRoomId("ROOM004");
//		roomVO2.setSellMemId("SELL002");
//		roomVO2.setRoomName("updateRoomName");
//		roomVO2.setRoomPrice(10000);
//		roomVO2.setRoomCapacity(4);
//		roomVO2.setRoomOnTime(new Timestamp(System.currentTimeMillis()));
//		roomVO2.setRoomDes("好山好水好無聊的漂亮民宿Update");
//		roomVO2.setRoomCollect(77);
//		roomVO2.setRoomStatus(1);
//		dao.update(roomVO2);
//
//		// 刪除
////		dao.delete("ROOM008");
//
//		// 查詢
//		RoomVO roomVO3 = dao.findByPrimaryKey("ROOM002");
//		System.out.print(roomVO3.getRoomId() + ",");
//		System.out.print(roomVO3.getSellMemId() + ",");
//		System.out.print(roomVO3.getRoomName() + ",");
//		System.out.print(roomVO3.getRoomPrice() + ",");
//		System.out.print(roomVO3.getRoomCapacity() + ",");
//		System.out.print(roomVO3.getRoomOnTime() + ",");
//		System.out.print(roomVO3.getRoomDes() + ",");
//		System.out.print(roomVO3.getRoomCollect() + ",");
//		System.out.println(roomVO3.getRoomStatus());
//		System.out.println("---------------------");
//
		// 查詢
		List<RoomVO> list = dao.getByDateRange(java.sql.Date.valueOf("2021-01-30"), java.sql.Date.valueOf("2021-01-30"));
		for (RoomVO room : list) {
			System.out.print(room.getRoomId() + ",");
			System.out.print(room.getSellMemId() + ",");
			System.out.print(room.getRoomName() + ",");
			System.out.print(room.getRoomPrice() + ",");
			System.out.print(room.getRoomCapacity() + ",");
			System.out.print(room.getRoomOnTime() + ",");
			System.out.print(room.getRoomDes() + ",");
			System.out.print(room.getRoomCollect() + ",");
			System.out.println(room.getRoomStatus());
			System.out.println();
		}
	}



}
