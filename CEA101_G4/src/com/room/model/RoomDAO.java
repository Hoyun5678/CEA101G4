package com.room.model;

import java.util.*;
import java.util.stream.Collectors;
import java.sql.*;
import java.sql.Date;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Room;


public class RoomDAO implements RoomDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	

	private static final String INSERT_STMT = 
		"INSERT INTO ROOM_PRODUCT(ROOM_ID, SELL_MEM_ID, ROOM_NAME, ROOM_PRICE, ROOM_CAPACITY, ROOM_DES, ROOM_STATUS, ROOM_ON_TIME)"
			+" VALUES ('ROOM' || LPAD(ROOM_SEQ.NEXTVAL, 3, '0'), ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM ROOM_PRODUCT ORDER BY ROOM_ID DESC";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM ROOM_PRODUCT WHERE ROOM_ID = ?";
	private static final String DELETE = 
		"DELETE FROM ROOM_PRODUCT WHERE ROOM_ID = ?";
	private static final String UPDATE = 
		"UPDATE ROOM_PRODUCT SET SELL_MEM_ID=?, ROOM_NAME=?, ROOM_PRICE=?, ROOM_CAPACITY=?, ROOM_ON_TIME=?, ROOM_DES=?, ROOM_COLLECT=?, ROOM_STATUS=? WHERE ROOM_ID = ?";
	private static final String UPDATE_BY_SELLMEM = 
			"UPDATE ROOM_PRODUCT SET ROOM_NAME=?, ROOM_PRICE=?, ROOM_CAPACITY=?, ROOM_ON_TIME=?, ROOM_DES=?, ROOM_STATUS=? WHERE ROOM_ID = ?";
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
			con = ds.getConnection();
			
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

			con = ds.getConnection();
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
	public RoomVO updateRoomBySell(RoomVO roomVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		RoomService roomSvc = new RoomService();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_BY_SELLMEM);
			
			pstmt.setString(1, roomVO.getRoomName());
			pstmt.setInt(2, roomVO.getRoomPrice());
			pstmt.setInt(3, roomVO.getRoomCapacity());
			pstmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			pstmt.setString(5, roomVO.getRoomDes());
			pstmt.setInt(6, roomVO.getRoomStatus());
			pstmt.setString(7, roomVO.getRoomId());

			pstmt.executeUpdate();
			return roomSvc.getOneRoom(roomVO.getRoomId());

			// Handle any driver errors
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, roomId);
			pstmt.executeUpdate();

			// Handle any driver errors
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
			con = ds.getConnection();

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
			con = ds.getConnection();
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
			con = ds.getConnection();

			String finalSQL = "SELECT * FROM ROOM_PRODUCT LEFT JOIN SELLER_MEMBER ON ROOM_PRODUCT.SELL_MEM_ID = SELLER_MEMBER.SELL_MEM_ID "
					+ jdbcUtil_CompositeQuery_Room.get_WhereCondition(map);
			
			pstmt = con.prepareStatement(finalSQL);
//			System.out.println("finalSQL = " + finalSQL);
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
			con = ds.getConnection();
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
			con = ds.getConnection();
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
	

}
