package com.souvenir_order_detail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.emp.model.EmpJDBCDAO;
import com.emp.model.EmpVO;


public class SouvenirOrderDetailJDBCDAO implements SouvenirOrderDetailDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G4";
	String passwd = "CEA101G4";
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, sodVO.getSou_order_id());
			pstmt.setString(2, sodVO.getSou_id());
			pstmt.setInt(3, sodVO.getSou_order_amount());
			pstmt.setInt(4, sodVO.getSou_price());
			pstmt.executeUpdate();
			System.out.println("新增成功 insert method");

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
	public void delete(String sou_order_id, String sou_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, sou_order_id);
			pstmt.setString(2, sou_id);

			pstmt.executeUpdate();
			System.out.println("刪除成功 delete method");
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
	public SouvenirOrderDetailVO findByPrimaryKey(String sou_order_id) {
		// TODO Auto-generated method stub
		SouvenirOrderDetailVO sodVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, sou_order_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// souvenir_order_detailVO 也稱為 Domain objects
				sodVO = new SouvenirOrderDetailVO();
				sodVO.setSou_order_id(rs.getString("sou_order_id"));
				sodVO.setSou_id(rs.getString("sou_id"));
				sodVO.setSou_order_amount(rs.getInt("sou_order_amount"));
				sodVO.setSou_price(rs.getInt("sou_price"));
		
				
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
		return sodVO;
	}
	@Override
	public void update(SouvenirOrderDetailVO sodVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, sodVO.getSou_id());
			pstmt.setInt(2, sodVO.getSou_order_amount());
			pstmt.setInt(3, sodVO.getSou_price());
			pstmt.setString(4, sodVO.getSou_order_id());
			

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
	public List<SouvenirOrderDetailVO> getAll() {
		// TODO Auto-generated method stub
		List<SouvenirOrderDetailVO> list = new ArrayList<SouvenirOrderDetailVO>();
		SouvenirOrderDetailVO sodVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// souvenir_order_detailVO 也稱為 Domain objects
				sodVO = new SouvenirOrderDetailVO();
				sodVO.setSou_order_id(rs.getString("sou_order_id"));
				sodVO.setSou_id(rs.getString("sou_id"));
				sodVO.setSou_order_amount(rs.getInt("sou_order_amount"));
				sodVO.setSou_price(rs.getInt("sou_price"));
				;
				list.add(sodVO); // Store the row in the list
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
			SouvenirOrderDetailJDBCDAO dao = new SouvenirOrderDetailJDBCDAO();
			//新增
//			SouvenirOrderDetailVO souvenirorderdetailVO1 = new SouvenirOrderDetailVO();
//			souvenirorderdetailVO1.setSou_order_id("SO001");
//			souvenirorderdetailVO1.setSou_id("SOU003");
//			souvenirorderdetailVO1.setSou_order_amount(30);
//			souvenirorderdetailVO1.setSou_price(40);
//			dao.insert(souvenirorderdetailVO1);
			//查詢
//			List<SouvenirOrderDetailVO> list = dao.getAll();
//			for(SouvenirOrderDetailVO aSouvenirOrderDetail : list) {
//				System.out.print("產品訂單編號:"+ aSouvenirOrderDetail.getSou_order_id() + ",");
//				System.out.print("產品編號:"+ aSouvenirOrderDetail.getSou_id() + ",");
//				System.out.print("產品數量" + aSouvenirOrderDetail.getSou_order_amount() + ",");
//				System.out.println("產品價格:" + aSouvenirOrderDetail.getSou_price());
//			}
			//刪除
//			dao.delete("SO001","SOU001");
//			EmpVO emp_authVO3 = dao.findByPrimaryKey("EMP002");
//			System.out.print(emp_authVO3.getEmp_id() + ",");
			SouvenirOrderDetailVO souvenirorderdetailVO2 = new SouvenirOrderDetailVO();
			souvenirorderdetailVO2.setSou_id("SOU003");
			souvenirorderdetailVO2.setSou_order_amount(30);
			souvenirorderdetailVO2.setSou_price(40);
			souvenirorderdetailVO2.setSou_order_id("SO001");
			dao.update(souvenirorderdetailVO2);
			
		}
	}	