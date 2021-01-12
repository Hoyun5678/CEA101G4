package com.roomorderdetail.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.roomordereddate.model.RoomOrderedDateDAO;
import com.roomordereddate.model.RoomOrderedDateJDBCDAO;
import com.roomordereddate.model.RoomOrderedDateVO;

public class RoomOrderDetailDAO implements RoomOrderDetailDAO_interface {
	
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
	public void insert(RoomOrderDetailVO rodVO) {
		PreparedStatement pstmt = null;
		Connection con = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, rodVO.getRoom_order_id());
			pstmt.setString(2, rodVO.getRoom_id());
			pstmt.setInt(3, rodVO.getRoom_cur_price());
			pstmt.setString(4, rodVO.getRoom_guest_name());
			pstmt.setString(5, rodVO.getRoom_guest_mail());
			pstmt.setString(6, rodVO.getRoom_guest_tel());
			

			pstmt.executeUpdate();

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
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, rodVO.getRoom_cur_price());
			pstmt.setString(2, rodVO.getRoom_guest_name());
			pstmt.setString(3, rodVO.getRoom_guest_mail());
			pstmt.setString(4, rodVO.getRoom_guest_tel());
			pstmt.setString(5, rodVO.getRoom_order_id());
			pstmt.setString(6, rodVO.getRoom_id());
			
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
	public void delete(String room_order_id, String room_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);


			pstmt.setString(1, room_order_id);
			pstmt.setString(2, room_id);

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
	public RoomOrderDetailVO findByPrimaryKey(String room_order_id) {

		RoomOrderDetailVO rodVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, room_order_id);
			System.out.println("room order id = " + room_order_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				rodVO = new RoomOrderDetailVO();
				rodVO.setRoom_order_id(rs.getString("room_order_id"));
				System.out.println("2room order id = " + room_order_id);
				rodVO.setRoom_id(rs.getString("room_id"));
				System.out.println("room id = ");
				rodVO.setRoom_cur_price(rs.getInt("room_cur_price"));
				System.out.println("room_cur_price = ");
				rodVO.setRoom_guest_name(rs.getString("room_guest_name"));
				System.out.println("room_guest_name = ");
				rodVO.setRoom_guest_mail(rs.getString("room_guest_mail"));
				System.out.println("room_guest_mail = ");
				rodVO.setRoom_guest_tel(rs.getString("room_guest_tel"));	
				System.out.println("room_guest_tel = ");
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

			con = ds.getConnection();
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
	public void insertFromOrder(RoomOrderDetailVO rodVO, java.sql.Connection con, Date checkInDate, Date checkOutDate) {
		PreparedStatement pstmt = null;
		
		
		try {
			con = ds.getConnection();
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
			
			System.out.println("1. order detail DAO -> insrt from order()");
			RoomOrderedDateDAO dao = new RoomOrderedDateDAO();
			long checkInDateToLong = checkInDate.getTime();
			RoomOrderedDateVO aRoomOrderedDate = null;
			System.out.println("2. order detail DAO -> insrt from order()");
			do {
				aRoomOrderedDate = new RoomOrderedDateVO();
				aRoomOrderedDate.setRoomOrderId(roomOrderId);
				aRoomOrderedDate.setRoomId(roomId);
				Date d = new Date(checkInDateToLong);
				aRoomOrderedDate.setRoomOrderDate(d);
				System.out.println("roomOrderId = " + roomOrderId + " , roomId = " + roomId + " , Date = " + d.toString());
				dao.insertFromOrderDetail(aRoomOrderedDate, con);
				checkInDateToLong += 24 * 60 * 60 * 1000L;
			} while(checkInDateToLong < checkOutDate.getTime());

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
}