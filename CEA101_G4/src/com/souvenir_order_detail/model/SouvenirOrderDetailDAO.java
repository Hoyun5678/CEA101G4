package com.souvenir_order_detail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_SouvenirOrderDetail;
public class SouvenirOrderDetailDAO implements SouvenirOrderDetailDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CEA101G4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO SOUVENIR_ORDER_DETAIL(SOU_ORDER_ID, SOU_ID, SOU_ORDER_AMOUNT, SOU_PRICE) VALUES(?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM SOUVENIR_ORDER_DETAIL ORDER BY SOU_ORDER_ID";
	private static final String GET_ONE_STMT = "SELECT * FROM SOUVENIR_ORDER_DETAIL WHERE SOU_ORDER_ID = ?";
	private static final String DELETE = "DELETE FROM SOUVENIR_ORDER_DETAIL WHERE SOU_ORDER_ID = ? AND SOU_ID = ?";
	private static final String UPDATE = "UPDATE SOUVENIR_ORDER_DETAIL SET  SOU_ID = ?, SOU_ORDER_AMOUNT = ?,  SOU_PRICE = ? WHERE SOU_ORDER_ID = ?";
	@Override
	public void insert(SouvenirOrderDetailVO sodVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, sodVO.getSou_order_id());
			pstmt.setString(2, sodVO.getSou_id());
			pstmt.setInt(3, sodVO.getSou_order_amount());
			pstmt.setInt(4, sodVO.getSou_price());
			pstmt.executeUpdate();
			System.out.println("�憓��� insert method");

			// Handle any driver errors
		}  catch (SQLException se) {
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
	public void delete(String sou_order_id, String sou_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, sou_order_id);
			pstmt.setString(2, sou_id);

			pstmt.executeUpdate();
			System.out.println("������ delete method");
			// Handle any driver errors
		}  catch (SQLException se) {
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
	public SouvenirOrderDetailVO findByPrimaryKey(String sou_order_id) {
		// TODO Auto-generated method stub
		SouvenirOrderDetailVO sodVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, sou_order_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// souvenir_order_detailVO 銋迂� Domain objects
				sodVO = new SouvenirOrderDetailVO();
				sodVO.setSou_order_id(rs.getString("sou_order_id"));
				sodVO.setSou_id(rs.getString("sou_id"));
				sodVO.setSou_order_amount(rs.getInt("sou_order_amount"));
				sodVO.setSou_price(rs.getInt("sou_price"));
		
				
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
		return sodVO;
	}
	@Override
	public void update(SouvenirOrderDetailVO sodVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, sodVO.getSou_id());
			pstmt.setInt(2, sodVO.getSou_order_amount());
			pstmt.setInt(3, sodVO.getSou_price());
			pstmt.setString(4, sodVO.getSou_order_id());
			

			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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
	public List<SouvenirOrderDetailVO> getAll() {
		// TODO Auto-generated method stub
		List<SouvenirOrderDetailVO> list = new ArrayList<SouvenirOrderDetailVO>();
		SouvenirOrderDetailVO sodVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// souvenir_order_detailVO 銋迂� Domain objects
				sodVO = new SouvenirOrderDetailVO();
				sodVO.setSou_order_id(rs.getString("sou_order_id"));
				sodVO.setSou_id(rs.getString("sou_id"));
				sodVO.setSou_order_amount(rs.getInt("sou_order_amount"));
				sodVO.setSou_price(rs.getInt("sou_price"));
				;
				list.add(sodVO); // Store the row in the list
			}

			// Handle any driver errors
		}  catch (SQLException se) {
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
	public List<SouvenirOrderDetailVO> getAll(Map<String, String[]> map) {
		List<SouvenirOrderDetailVO> list = new ArrayList<SouvenirOrderDetailVO>();
		SouvenirOrderDetailVO sodVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			con = ds.getConnection();
			String finalSQL = "select * from SOUVENIR_ORDER_DETAIL "
		          + jdbcUtil_CompositeQuery_SouvenirOrderDetail.get_WhereCondition(map)
		          + "order by sou_order_id";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				sodVO = new SouvenirOrderDetailVO();
				sodVO.setSou_order_id(rs.getString("sou_order_id"));
				sodVO.setSou_id(rs.getString("sou_id"));
				sodVO.setSou_order_amount(rs.getInt("sou_order_amount"));
				sodVO.setSou_price(rs.getInt("sou_price"));
				list.add(sodVO); // Store the row in the list
			}
	
			// Handle any SQL errors
		}
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	 public void insert2 (SouvenirOrderDetailVO sodVO , java.sql.Connection con) {
		PreparedStatement pstmt = null;

		try {

    		pstmt = con.prepareStatement(INSERT_STMT);

    		pstmt.setString(1, sodVO.getSou_order_id());
			pstmt.setString(2, sodVO.getSou_id());
			pstmt.setInt(3, sodVO.getSou_order_amount());
			pstmt.setInt(4, sodVO.getSou_price());
			pstmt.executeUpdate();

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-SO");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
