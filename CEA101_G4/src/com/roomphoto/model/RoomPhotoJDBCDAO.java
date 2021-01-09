package com.roomphoto.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.room.model.RoomJDBCDAO;
import com.room.model.RoomVO;

import util.Util;

public class RoomPhotoJDBCDAO implements RoomPhotoDAO_interface{
	private static final String INSERT_STMT = 
		"INSERT INTO ROOM_PHOTO(ROOM_PHOTO_ID, ROOM_ID, ROOM_PHOTO, ROOM_PHOTO_CONTENT) "
			+ "VALUES('ROOMPH' || LPAD(ROOMPHO_SEQ.NEXTVAL, 3, '0'), ?, ?, ?) ";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM ROOM_PHOTO ORDER BY ROOM_PHOTO_ID";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM ROOM_PHOTO WHERE ROOM_PHOTO_ID = ?";
	private static final String DELETE = 
		"DELETE FROM ROOM_PHOTO WHERE ROOM_PHOTO_ID = ?";
	private static final String UPDATE = 
		"UPDATE ROOM_PHOTO SET ROOM_ID=?, ROOM_PHOTO=?, ROOM_PHOTO_CONTENT=? WHERE ROOM_PHOTO_ID = ?";
	private static final String GET_BYROOMID = 
		"SELECT * FROM ROOM_PHOTO WHERE ROOM_ID = ?";
	
	public void insert(RoomPhotoVO roomPhotoVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, roomPhotoVO.getRoomId());
			pstmt.setBytes(2, roomPhotoVO.getRoomPhoto());
			pstmt.setString(3, roomPhotoVO.getRoomPhotoContent());

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
	public void update(RoomPhotoVO roomPhotoVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, roomPhotoVO.getRoomId());
			pstmt.setBytes(2, roomPhotoVO.getRoomPhoto());
			pstmt.setString(3, roomPhotoVO.getRoomPhotoContent());
			pstmt.setString(4, roomPhotoVO.getRoomPhotoId());

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
	public void delete(String roomPhotoId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, roomPhotoId);

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
	public RoomPhotoVO findByPrimaryKey(String roomPhotoId) {
		
		RoomPhotoVO roomPhotoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, roomPhotoId);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				roomPhotoVO = new RoomPhotoVO();
				roomPhotoVO.setRoomPhotoId(rs.getString("ROOM_PHOTO_ID"));
				roomPhotoVO.setRoomId(rs.getString("ROOM_ID"));
				roomPhotoVO.setRoomPhoto(rs.getBytes("ROOM_PHOTO"));
				roomPhotoVO.setRoomPhotoContent(rs.getString("ROOM_PHOTO_CONTENT"));
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
		
		return roomPhotoVO;
	}


	@Override
	public List<RoomPhotoVO> getAll() {
		List<RoomPhotoVO> list = new ArrayList<RoomPhotoVO>();
		RoomPhotoVO roomPhotoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				roomPhotoVO = new RoomPhotoVO();
				roomPhotoVO.setRoomPhotoId(rs.getString("ROOM_PHOTO_ID"));
				roomPhotoVO.setRoomId(rs.getString("ROOM_ID"));
//				roomPhotoVO.setRoomPhoto(rs.getBytes("ROOM_PHOTO"));
				roomPhotoVO.setRoomPhotoContent(rs.getString("ROOM_PHOTO_CONTENT"));
				list.add(roomPhotoVO);
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
	public List<RoomPhotoVO> getByRoomId(String roomId) {
		List<RoomPhotoVO> list = new ArrayList<RoomPhotoVO>();
		RoomPhotoVO roomPhotoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_BYROOMID);
			pstmt.setString(1, roomId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				roomPhotoVO = new RoomPhotoVO();
				roomPhotoVO.setRoomPhotoId(rs.getString("ROOM_PHOTO_ID"));
				roomPhotoVO.setRoomId(rs.getString("ROOM_ID"));
				roomPhotoVO.setRoomPhoto(rs.getBytes("ROOM_PHOTO"));
				roomPhotoVO.setRoomPhotoContent(rs.getString("ROOM_PHOTO_CONTENT"));
				list.add(roomPhotoVO);
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

		// 查詢
		List<RoomVO> list = dao.getAll();
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
