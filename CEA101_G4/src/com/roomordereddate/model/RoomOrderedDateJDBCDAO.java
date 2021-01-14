package com.roomordereddate.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Util;

public class RoomOrderedDateJDBCDAO implements RoomOrderedDateDAO_interface {
	private static final String INSERT_STMT = 
		"INSERT INTO ROOM_ORDERED_DATE_RECORD(ROOM_ORDERED_DATE_ID, ROOM_ID, ROOM_ORDER_ID, ROOM_ORDERED_DATE) "
		+ "VALUES('RODR' || LPAD(ROOM_ORDERED_SEQ.NEXTVAL, 3, '0'), ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM ROOM_ORDERED_DATE_RECORD";
	private static final String GET_BYROOMID_STMT = 
			"SELECT * FROM ROOM_ORDERED_DATE_RECORD WHERE ROOM_ID=?";
	
	private static final String DELETE = 
			"DELETE FROM ROOM_ORDERED_DATE_RECORD WHERE ROOM_ORDERED_DATE_ID = ?";
	
	
	@Override
	public void insertFromOrderDetail(RoomOrderedDateVO roomOrderedDateVO, Connection con) {
		PreparedStatement pstmt = null;

		try {
			System.out.println("1. orderdate jdbcdao");
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, roomOrderedDateVO.getRoomId());
			pstmt.setString(2, roomOrderedDateVO.getRoomOrderId());
			pstmt.setDate(3, roomOrderedDateVO.getRoomOrderDate());
			
			pstmt.executeUpdate();

			System.out.println("2. orderdate jdbcdao");
			
			// Handle any driver errors
		} catch (SQLException se) {
			System.out.println("orderedDate DAO error");
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
		}
	}


	@Override
	public RoomOrderedDateVO insert(RoomOrderedDateVO roomOrderedDateVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, roomOrderedDateVO.getRoomId());
			pstmt.setString(2, roomOrderedDateVO.getRoomOrderId());
			pstmt.setDate(3, roomOrderedDateVO.getRoomOrderDate());

			pstmt.executeUpdate();
			
			return roomOrderedDateVO;
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
	public void delete(String roomOrderedDateId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, roomOrderedDateId);

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
	public List<RoomOrderedDateVO> getAll() {
		List<RoomOrderedDateVO> list = new ArrayList<RoomOrderedDateVO>();
		RoomOrderedDateVO roomOrderedDateVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomOrderedDateVO = new RoomOrderedDateVO();
				roomOrderedDateVO.setRoomOrderedDateId(rs.getString("ROOM_ORDERED_DATE_ID"));
				roomOrderedDateVO.setRoomId(rs.getString("ROOM_ID"));
				roomOrderedDateVO.setRoomOrderId(rs.getString("ROOM_ORDER_ID"));
				roomOrderedDateVO.setRoomOrderDate(rs.getDate("ROOM_ORDERED_DATE"));
				list.add(roomOrderedDateVO);
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
	public List<String> getByRoomId(String roomId) {
		List<String> list = new ArrayList<String>();
		RoomOrderedDateVO roomOrderedDateVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_BYROOMID_STMT);
			pstmt.setString(1, roomId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomOrderedDateVO = new RoomOrderedDateVO();
				roomOrderedDateVO.setRoomOrderedDateId(rs.getString("ROOM_ORDERED_DATE_ID"));
				roomOrderedDateVO.setRoomId(rs.getString("ROOM_ID"));
				roomOrderedDateVO.setRoomOrderId(rs.getString("ROOM_ORDER_ID"));
				roomOrderedDateVO.setRoomOrderDate(rs.getDate("ROOM_ORDERED_DATE"));
				list.add(roomOrderedDateVO.getRoomOrderedDateId());
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
		return null;
	}


	@Override
	public List<String> getBySellMemId(String sellMemId) {
		// TODO Auto-generated method stub
		return null;
	}



	
	

}
