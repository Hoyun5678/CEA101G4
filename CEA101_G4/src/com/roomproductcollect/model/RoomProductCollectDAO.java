package com.roomproductcollect.model;

import java.sql.Connection;
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

import com.roomorderdetail.model.RoomOrderDetailVO;

public class RoomProductCollectDAO implements RoomProductCollectDAO_interface {
	
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
			"INSERT INTO Room_Product_Collect(MEM_ID, ROOM_ID)"
			+ " VALUES (?,?)";
	private static final String DELETE = 
			"DELETE FROM Room_Product_Collect where MEM_ID = ? AND ROOM_ID=?";
	private static final String GET_COUNT_COLLECT =
			"SELECT COUNT(ROOM_ID) AS collect FROM ROOM_PRODUCT_COLLECT where ROOM_ID =?";
	private static final String GETALL =
			"SELECT MEM_ID, ROOM_ID FROM ROOM_PRODUCT_COLLECT order by MEM_ID";
	private static final String GETONEBYMEMID =
			"SELECT ROOM_ID FROM ROOM_PRODUCT_COLLECT where MEM_ID = ?";
	private static final String GETONEBYROOMID =
			"SELECT MEM_ID FROM ROOM_PRODUCT_COLLECT where ROOM_ID = ?";

	
	
	@Override
	public void insert(String mem_id,String room_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, mem_id);
			pstmt.setString(2, room_id);
			
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
	public void delete(String mem_id, String room_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, mem_id);
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
	public List<String> findByMemId(String mem_id) {
		List<String> list = new ArrayList<String>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GETONEBYMEMID);

			pstmt.setString(1, mem_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				list.add(rs.getString("room_id"));
							
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
	public List<String> findByRoomId(String room_id) {
		List<String>list = new ArrayList<String>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GETONEBYROOMID);

			pstmt.setString(1,room_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				list.add(rs.getString("mem_id"));
							
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
	public List<RoomProductCollectVO> getAll() {
		List<RoomProductCollectVO> list = new ArrayList<RoomProductCollectVO>();
		RoomProductCollectVO rpcVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GETALL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
			
				rpcVO = new RoomProductCollectVO();
				rpcVO.setMem_id(rs.getString("mem_id"));
				rpcVO.setRoom_id(rs.getString("room_id"));
				list.add(rpcVO);
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
	public int countCollect(String room_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int collect =0;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_COUNT_COLLECT);
			pstmt.setString(1, room_id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				collect = rs.getInt("collect");
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
		return collect;
	}


	
}
