package com.roomorderdetail.model;
import java.util.*;

import com.roomordereddate.model.*;

import util.Util;

import java.sql.*;
import java.sql.Date;


public class RoomOrderDetailJDBCDAO implements RoomOrderDetailDAO_interface {

	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String userid = "CEA101G4";
	private static final String passwd = "CEA101G4";

	private static final String INSERT_STMT =
			"INSERT INTO ROOM_ORDER_DETAIL(ROOM_ORDER_ID, ROOM_ID, ROOM_CUR_PRICE, ROOM_GUEST_NAME, ROOM_GUEST_MAIL, ROOM_GUEST_TEL)"
			+ " VALUES (?,?,?,?,?,?)";
	private static final String UPDATE = 
			"UPDATE ROOM_ORDER_DETAIL set ROOM_CUR_PRICE=?,ROOM_GUEST_NAME=?, ROOM_GUEST_MAIL=?, ROOM_GUEST_TEL=? where ROOM_ORDER_ID=? and ROOM_ID =?";
	private static final String DELETE = 
			"DELETE FROM ROOM_ORDER_DETAIL where ROOM_ORDER_ID = ? AND ROOM_ID=?";
	private static final String GET_ONE_STMT =
			"SELECT ROOM_ORDER_ID, ROOM_ID, ROOM_CUR_PRICE, ROOM_GUEST_NAME, ROOM_GUEST_MAIL, ROOM_GUEST_TEL FROM ROOM_ORDER_DETAIL where ROOM_ORDER_ID = ?";
	private static final String GET_ALL_STMT =
			"SELECT ROOM_ORDER_ID, ROOM_ID, ROOM_CUR_PRICE, ROOM_GUEST_NAME, ROOM_GUEST_MAIL, ROOM_GUEST_TEL FROM ROOM_ORDER_DETAIL order by ROOM_ORDER_ID";
	
		
	@Override
	public void insertFromOrder(RoomOrderDetailVO rodVO, java.sql.Connection con, Date checkInDate, Date checkOutDate) {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(INSERT_STMT);
			
			String roomOrderId = rodVO.getRoom_order_id();
			String roomId = rodVO.getRoom_id();

			pstmt.setString(1, rodVO.getRoom_order_id());
			pstmt.setString(2, rodVO.getRoom_id());
			pstmt.setInt(3, rodVO.getRoom_cur_price());
			pstmt.setString(4, rodVO.getRoom_guest_name());
			pstmt.setString(5, rodVO.getRoom_guest_mail());
			pstmt.setString(6, rodVO.getRoom_guest_tel());
			
			pstmt.executeUpdate();
			
			RoomOrderedDateJDBCDAO dao = new RoomOrderedDateJDBCDAO();
			long checkInDateToLong = checkInDate.getTime();
			RoomOrderedDateVO aRoomOrderedDate = null;
			do {
				aRoomOrderedDate = new RoomOrderedDateVO();
				aRoomOrderedDate.setRoomOrderId(roomOrderId);
				aRoomOrderedDate.setRoomId(roomId);
				aRoomOrderedDate.setRoomOrderDate(new Date(checkInDateToLong));
				dao.insertFromOrderDetail(aRoomOrderedDate, con);
				checkInDateToLong += 24 * 60 * 60 * 1000L;
			} while(checkInDateToLong < checkOutDate.getTime());
			
//			con.commit();
//			con.setAutoCommit(true);

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
		}

	}
	
	@Override
	public void insert(RoomOrderDetailVO rodVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, rodVO.getRoom_order_id());
			pstmt.setString(2, rodVO.getRoom_id());
			pstmt.setInt(3, rodVO.getRoom_cur_price());
			pstmt.setString(4, rodVO.getRoom_guest_name());
			pstmt.setString(5, rodVO.getRoom_guest_mail());
			pstmt.setString(6, rodVO.getRoom_guest_tel());
			

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
	public void update(RoomOrderDetailVO rodVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, rodVO.getRoom_cur_price());
			pstmt.setString(2, rodVO.getRoom_guest_name());
			pstmt.setString(3, rodVO.getRoom_guest_mail());
			pstmt.setString(4, rodVO.getRoom_guest_tel());
			pstmt.setString(5, rodVO.getRoom_order_id());
			pstmt.setString(6, rodVO.getRoom_id());
			
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
	public void delete(String room_order_id, String room_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, room_order_id);
			pstmt.setString(2, room_id);

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
	public RoomOrderDetailVO findByPrimaryKey(String room_order_id) {

		RoomOrderDetailVO rodVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, room_order_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				rodVO = new RoomOrderDetailVO();
				rodVO.setRoom_order_id(rs.getString("room_order_id"));
				rodVO.setRoom_id(rs.getString("room_id"));
				rodVO.setRoom_cur_price(rs.getInt("room_cur_price"));
				rodVO.setRoom_guest_name(rs.getString("room_guest_name"));
				rodVO.setRoom_guest_mail(rs.getString("room_guest_mail"));
				rodVO.setRoom_guest_tel(rs.getString("room_guest_tel"));
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
		return rodVO;
	}

	@Override
	public List<RoomOrderDetailVO> getAll() {
		List<RoomOrderDetailVO> list = new ArrayList<RoomOrderDetailVO>();
		RoomOrderDetailVO rodVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				rodVO = new RoomOrderDetailVO();
				rodVO.setRoom_order_id(rs.getString("room_order_id"));
				rodVO.setRoom_id(rs.getString("room_id"));
				rodVO.setRoom_cur_price(rs.getInt("room_cur_price"));
				rodVO.setRoom_guest_name(rs.getString("room_guest_name"));
				rodVO.setRoom_guest_mail(rs.getString("room_guest_mail"));
				rodVO.setRoom_guest_tel(rs.getString("room_guest_tel"));
				list.add(rodVO);
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

		RoomOrderDetailJDBCDAO dao = new RoomOrderDetailJDBCDAO();
		
//		// 新增(可以跑)
//		RoomOrderDetailVO rodVO1 = new RoomOrderDetailVO();
//		rodVO1.setRoom_order_id("RO002");
//		rodVO1.setRoom_id("ROOM001");
//		rodVO1.setRoom_cur_price(2480);
//		dao.insert(rodVO1);

		
		// 修改
		RoomOrderDetailVO rodVO2 = new RoomOrderDetailVO();
		rodVO2.setRoom_order_id("RO003");
		rodVO2.setRoom_id("ROOM001");
		rodVO2.setRoom_cur_price(1799);
		
		dao.update(rodVO2);
		
		// 刪除
//		dao.delete("RO001","ROOM001");
		
//		// 查詢
//		RoomOrderDetailVO rodVO3 = dao.findByPrimaryKey("RO002");
//		System.out.print(rodVO3.getRoom_id() + ",");
//		System.out.print(rodVO3.getRoom_cur_price() + ",");
//		System.out.println();
//		System.out.println("---------------------");
//
//		// 查詢
//		List<RoomOrderDetailVO> list = dao.getAll();
//		for (RoomOrderDetailVO rodVO4 : list) {
//			System.out.print(rodVO4.getRoom_id() + ",");
//			System.out.print(rodVO4.getRoom_cur_price() + ",");
//
//			System.out.println();
//		}
//		
//		
	}
	}