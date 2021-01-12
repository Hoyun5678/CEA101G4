package com.roomphoto.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.room.model.RoomJDBCDAO;
import com.room.model.RoomVO;

import util.Util;

public class RoomPhotoDAO implements RoomPhotoDAO_interface{
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, roomPhotoVO.getRoomId());
			pstmt.setBytes(2, roomPhotoVO.getRoomPhoto());
			pstmt.setString(3, roomPhotoVO.getRoomPhotoContent());

			pstmt.executeUpdate();

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
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, roomPhotoVO.getRoomId());
			pstmt.setBytes(2, roomPhotoVO.getRoomPhoto());
			pstmt.setString(3, roomPhotoVO.getRoomPhotoContent());
			pstmt.setString(4, roomPhotoVO.getRoomPhotoId());

			pstmt.executeUpdate();

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
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, roomPhotoId);

			pstmt.executeUpdate();

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
			con = ds.getConnection();
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
			con = ds.getConnection();
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
			con = ds.getConnection();
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

}
