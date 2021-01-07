package com.roomorder.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.room.model.RoomVO;
import com.roomorderdetail.model.RoomOrderDetailDAO;
import com.roomorderdetail.model.RoomOrderDetailVO;

import util.Util;

public class RoomOrderJDBCDAO implements RoomOrderDAO_interface{
	
	private static final String INSERT_STMT = 
			"INSERT INTO ROOM_ORDER(ROOM_ORDER_ID, SELL_MEM_ID, MEM_ID, ROOM_ORDER_TIME, CHECK_IN_DATE, CHECK_OUT_DATE, EXPECT_ARR_TIME, ROOM_ORDER_REMARKS, ROOM_ORDER_SUM, ROOM_ORDER_STATUS, ROOM_PAYMENT_STATUS) "
			+ "VALUES('RO' || LPAD(RO_SEQ.NEXTVAL, 3, '0'), ?, ?, CURRENT_TIMESTAMP, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM ROOM_ORDER ORDER BY ROOM_ORDER_ID";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM ROOM_ORDER WHERE ROOM_ORDER_ID=?";
	private static final String DELETE = 
			"DELETE FROM ROOM_ORDER WHERE ROOM_ORDER_ID=?";
	private static final String UPDATE_ORDERSTATUS = 
			"UPDATE ROOM_ORDER SET ROOM_ORDER_STATUS=? WHERE ROOM_ORDER_ID=?";
	private static final String UPDATE_PAYSTATUS = 
			"UPDATE ROOM_ORDER SET ROOM_ORDER_STATUS=?, ROOM_PAYMENT_STATUS=? WHERE ROOM_ORDER_ID=?";
	private static final String GET_BYSELLMEMID_STMT = 
			"SELECT * FROM ROOM_ORDER WHERE SELL_MEM_ID = ? ORDER BY ROOM_ORDER_STATUS DESC, CHECK_IN_DATE DESC";
	private static final String GET_BYSELLMEMID_DATE_STMT = 
			"SELECT * FROM ROOM_ORDER WHERE SELL_MEM_ID = ? AND CHECK_IN_DATE = to_date(?, 'YYYY-MM-DD') ORDER BY ROOM_ORDER_STATUS DESC";
	private static final String GET_BYMEMID_STMT = 
			"SELECT * FROM ROOM_ORDER WHERE MEM_ID = ? ORDER BY ROOM_ORDER_STATUS DESC, CHECK_IN_DATE DESC";
	
	

	@Override
	public void insert(RoomOrderVO roomOrderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, roomOrderVO.getSellMemId());
			pstmt.setString(2, roomOrderVO.getMemId());
//			pstmt.setTimestamp(3, roomOrderVO.getRoomOrderTime());
			pstmt.setDate(3, roomOrderVO.getCheckInDate());
			pstmt.setDate(4, roomOrderVO.getCheckOutDate());
			pstmt.setTimestamp(5, roomOrderVO.getExpectArrTime());
			pstmt.setString(6, roomOrderVO.getRoomOrderRemarks());
			pstmt.setInt(7, roomOrderVO.getRoomOrderSum());
			pstmt.setInt(8, roomOrderVO.getRoomOrderStatus());
			pstmt.setInt(9, roomOrderVO.getRoomPaymentStatus());

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
	public void updateOrderStatus(RoomOrderVO roomOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_ORDERSTATUS);


			pstmt.setInt(1, roomOrderVO.getRoomOrderStatus());
			pstmt.setString(2, roomOrderVO.getRoomOrderId());

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
	public void updatePaymentStatus(RoomOrderVO roomOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_PAYSTATUS);


			pstmt.setInt(1, roomOrderVO.getRoomOrderStatus());
			pstmt.setInt(2, roomOrderVO.getRoomPaymentStatus());
			pstmt.setString(3, roomOrderVO.getRoomOrderId());

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
	public void delete(String roomOrderId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, roomOrderId);

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
	public RoomOrderVO findByPrimaryKey(String roomOrderId) {
		
		RoomOrderVO roomOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, roomOrderId);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				roomOrderVO = new RoomOrderVO();
				roomOrderVO.setRoomOrderId(rs.getString("ROOM_ORDER_ID"));
				roomOrderVO.setSellMemId(rs.getString("SELL_MEM_ID"));
				roomOrderVO.setMemId(rs.getString("MEM_ID"));
				roomOrderVO.setRoomOrderTime(rs.getTimestamp("ROOM_ORDER_TIME"));
				roomOrderVO.setCheckInDate(rs.getDate("CHECK_IN_DATE"));
				roomOrderVO.setCheckOutDate(rs.getDate("CHECK_OUT_DATE"));
				roomOrderVO.setExpectArrTime(rs.getTimestamp("EXPECT_ARR_TIME"));
				roomOrderVO.setRoomOrderRemarks(rs.getString("ROOM_ORDER_REMARKS"));
				roomOrderVO.setRoomOrderSum(rs.getInt("ROOM_ORDER_SUM"));
				roomOrderVO.setRoomOrderStatus(rs.getInt("ROOM_ORDER_STATUS"));
				roomOrderVO.setRoomPaymentStatus(rs.getInt("ROOM_PAYMENT_STATUS"));
			}


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
		
		
		
		return roomOrderVO;
	}

	@Override
	public List<RoomOrderVO> getAll() {
		List<RoomOrderVO> list = new ArrayList<RoomOrderVO>();
		RoomOrderVO roomOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();

			while(rs.next()) {
				roomOrderVO = new RoomOrderVO();
				roomOrderVO.setRoomOrderId(rs.getString("ROOM_ORDER_ID"));
				roomOrderVO.setSellMemId(rs.getString("SELL_MEM_ID"));
				roomOrderVO.setMemId(rs.getString("MEM_ID"));
				roomOrderVO.setRoomOrderTime(rs.getTimestamp("ROOM_ORDER_TIME"));
				roomOrderVO.setCheckInDate(rs.getDate("CHECK_IN_DATE"));
				roomOrderVO.setCheckOutDate(rs.getDate("CHECK_OUT_DATE"));
				roomOrderVO.setExpectArrTime(rs.getTimestamp("EXPECT_ARR_TIME"));
				roomOrderVO.setRoomOrderRemarks(rs.getString("ROOM_ORDER_REMARKS"));
				roomOrderVO.setRoomOrderSum(rs.getInt("ROOM_ORDER_SUM"));
				roomOrderVO.setRoomOrderStatus(rs.getInt("ROOM_ORDER_STATUS"));
				roomOrderVO.setRoomPaymentStatus(rs.getInt("ROOM_PAYMENT_STATUS"));
				list.add(roomOrderVO);
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
	public List<RoomOrderVO> getBySellMemId(String sellMemId) {
		List<RoomOrderVO> list = new ArrayList<RoomOrderVO>();
		RoomOrderVO roomOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_BYSELLMEMID_STMT);
			
			pstmt.setString(1, sellMemId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				roomOrderVO = new RoomOrderVO();
				roomOrderVO.setRoomOrderId(rs.getString("ROOM_ORDER_ID"));
				roomOrderVO.setSellMemId(rs.getString("SELL_MEM_ID"));
				roomOrderVO.setMemId(rs.getString("MEM_ID"));
				roomOrderVO.setRoomOrderTime(rs.getTimestamp("ROOM_ORDER_TIME"));
				roomOrderVO.setCheckInDate(rs.getDate("CHECK_IN_DATE"));
				roomOrderVO.setCheckOutDate(rs.getDate("CHECK_OUT_DATE"));
				roomOrderVO.setExpectArrTime(rs.getTimestamp("EXPECT_ARR_TIME"));
				roomOrderVO.setRoomOrderRemarks(rs.getString("ROOM_ORDER_REMARKS"));
				roomOrderVO.setRoomOrderSum(rs.getInt("ROOM_ORDER_SUM"));
				roomOrderVO.setRoomOrderStatus(rs.getInt("ROOM_ORDER_STATUS"));
				roomOrderVO.setRoomPaymentStatus(rs.getInt("ROOM_PAYMENT_STATUS"));
				list.add(roomOrderVO);
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
	public List<RoomOrderVO> getBySellMemIdAndDate(String sellMemId, String selectedDate) {
		List<RoomOrderVO> list = new ArrayList<RoomOrderVO>();
		RoomOrderVO roomOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_BYSELLMEMID_DATE_STMT);
			
			pstmt.setString(1, sellMemId);
			pstmt.setString(2, selectedDate);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				roomOrderVO = new RoomOrderVO();
				roomOrderVO.setRoomOrderId(rs.getString("ROOM_ORDER_ID"));
				roomOrderVO.setSellMemId(rs.getString("SELL_MEM_ID"));
				roomOrderVO.setMemId(rs.getString("MEM_ID"));
				roomOrderVO.setRoomOrderTime(rs.getTimestamp("ROOM_ORDER_TIME"));
				roomOrderVO.setCheckInDate(rs.getDate("CHECK_IN_DATE"));
				roomOrderVO.setCheckOutDate(rs.getDate("CHECK_OUT_DATE"));
				roomOrderVO.setExpectArrTime(rs.getTimestamp("EXPECT_ARR_TIME"));
				roomOrderVO.setRoomOrderRemarks(rs.getString("ROOM_ORDER_REMARKS"));
				roomOrderVO.setRoomOrderSum(rs.getInt("ROOM_ORDER_SUM"));
				roomOrderVO.setRoomOrderStatus(rs.getInt("ROOM_ORDER_STATUS"));
				roomOrderVO.setRoomPaymentStatus(rs.getInt("ROOM_PAYMENT_STATUS"));
				list.add(roomOrderVO);
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
	
	public List<RoomOrderVO> getByMemId(String memId) {
		List<RoomOrderVO> list = new ArrayList<RoomOrderVO>();
		RoomOrderVO roomOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_BYMEMID_STMT);
			
			pstmt.setString(1, memId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				roomOrderVO = new RoomOrderVO();
				roomOrderVO.setRoomOrderId(rs.getString("ROOM_ORDER_ID"));
				roomOrderVO.setSellMemId(rs.getString("SELL_MEM_ID"));
				roomOrderVO.setMemId(rs.getString("MEM_ID"));
				roomOrderVO.setRoomOrderTime(rs.getTimestamp("ROOM_ORDER_TIME"));
				roomOrderVO.setCheckInDate(rs.getDate("CHECK_IN_DATE"));
				roomOrderVO.setCheckOutDate(rs.getDate("CHECK_OUT_DATE"));
				roomOrderVO.setExpectArrTime(rs.getTimestamp("EXPECT_ARR_TIME"));
				roomOrderVO.setRoomOrderRemarks(rs.getString("ROOM_ORDER_REMARKS"));
				roomOrderVO.setRoomOrderSum(rs.getInt("ROOM_ORDER_SUM"));
				roomOrderVO.setRoomOrderStatus(rs.getInt("ROOM_ORDER_STATUS"));
				roomOrderVO.setRoomPaymentStatus(rs.getInt("ROOM_PAYMENT_STATUS"));
				list.add(roomOrderVO);
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
	
	public RoomOrderVO insertWithDetail(RoomOrderVO roomOrderVO, List<RoomOrderDetailVO> list) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			
			
			String[] cols = new String[] {"ROOM_ORDER_ID"};
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(INSERT_STMT, cols);

			pstmt.setString(1, roomOrderVO.getSellMemId());
			pstmt.setString(2, roomOrderVO.getMemId());
//			pstmt.setTimestamp(3, roomOrderVO.getRoomOrderTime());
			pstmt.setDate(3, roomOrderVO.getCheckInDate());
			pstmt.setDate(4, roomOrderVO.getCheckOutDate());
			pstmt.setTimestamp(5, roomOrderVO.getExpectArrTime());
			pstmt.setString(6, roomOrderVO.getRoomOrderRemarks());
			pstmt.setInt(7, roomOrderVO.getRoomOrderSum());
			pstmt.setInt(8, roomOrderVO.getRoomOrderStatus());
			pstmt.setInt(9, roomOrderVO.getRoomPaymentStatus());

			pstmt.executeUpdate();
			
			String nextRoomOrderId = null;
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				nextRoomOrderId = rs.getString(1);
			}
			
			roomOrderVO.setRoomOrderId(nextRoomOrderId);
			
			rs.close();
			// insert detail
			RoomOrderDetailDAO dao = new RoomOrderDetailDAO();
			for (RoomOrderDetailVO aRoomOrderDetail : list) {
				aRoomOrderDetail.setRoom_order_id(nextRoomOrderId);
//				aRoomOrderDetail.setRoom_id(aRoomOrderDetail.getRoom_id());
				dao.insertFromOrder(aRoomOrderDetail, con);
			}
			con.commit();
			con.setAutoCommit(true);
			
			return roomOrderVO;

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
	

}
