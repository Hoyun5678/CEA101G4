package com.souvenir_order.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.souvenir_order_detail.model.SouvenirOrderDetailJDBCDAO;
import com.souvenir_order_detail.model.SouvenirOrderDetailVO;

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_SouvenirOrder;

public class SouvenirOrderJDBCDAO implements SouvenirOrderDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G4";
	String passwd = "CEA101G4";
	private static final String INSERT_STMT = "INSERT INTO SOUVENIR_ORDER(SOU_ORDER_ID, EMP_ID, MEM_ID, SOU_ORDER_DATE, SOU_RECEIVER_NAME, SOU_RECEIVER_ADDRESS, SOU_RECEIVER_PHONE, SOU_SHIPMENT_FEE, SOU_ORDER_SUM_PRICE, SOU_ORDER_REMARKS, SOU_SHIPPING_METHOD, SOU_ORDER_STATUS, SOU_PAYMENT_STATUS, SOU_SHIPMENT_STATUS) VALUES('SO' || LPAD(SO_SEQ.NEXTVAL, 3, '0'), ?, ?, current_TimeStamp, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT SOU_ORDER_ID, EMP_ID, MEM_ID, SOU_ORDER_DATE, SOU_RECEIVER_NAME, SOU_RECEIVER_ADDRESS, SOU_RECEIVER_PHONE, SOU_SHIPMENT_FEE, SOU_ORDER_SUM_PRICE, SOU_ORDER_REMARKS, SOU_SHIPPING_METHOD, SOU_ORDER_STATUS, SOU_PAYMENT_STATUS, SOU_SHIPMENT_STATUS FROM SOUVENIR_ORDER ORDER BY SOU_ORDER_ID";
	private static final String GET_ONE_STMT = "SELECT SOU_ORDER_ID, EMP_ID, MEM_ID, SOU_ORDER_DATE, SOU_RECEIVER_NAME, SOU_RECEIVER_ADDRESS, SOU_RECEIVER_PHONE, SOU_SHIPMENT_FEE, SOU_ORDER_SUM_PRICE, SOU_ORDER_REMARKS, SOU_SHIPPING_METHOD, SOU_ORDER_STATUS, SOU_PAYMENT_STATUS, SOU_SHIPMENT_STATUS FROM SOUVENIR_ORDER WHERE SOU_ORDER_ID = ?";
	private static final String DELETE = "DELETE FROM SOUVENIR_ORDER WHERE SOU_ORDER_ID = ?";
	private static final String UPDATE = "UPDATE SOUVENIR_ORDER SET SOU_RECEIVER_NAME = ?, SOU_RECEIVER_ADDRESS = ?, SOU_RECEIVER_PHONE = ?, SOU_SHIPMENT_FEE = ?, SOU_ORDER_SUM_PRICE = ?, SOU_ORDER_REMARKS= ? , SOU_SHIPPING_METHOD = ?, SOU_ORDER_STATUS = ?, SOU_PAYMENT_STATUS = ?, SOU_SHIPMENT_STATUS = ? WHERE SOU_ORDER_ID = ?";
	private static final String GET_BY_MEM_ID_STMT ="SELECT SOU_ORDER_ID, EMP_ID, MEM_ID, SOU_ORDER_DATE, SOU_RECEIVER_NAME, SOU_RECEIVER_ADDRESS, SOU_RECEIVER_PHONE, SOU_SHIPMENT_FEE, SOU_ORDER_SUM_PRICE, SOU_ORDER_REMARKS, SOU_SHIPPING_METHOD, SOU_ORDER_STATUS, SOU_PAYMENT_STATUS, SOU_SHIPMENT_STATUS FROM SOUVENIR_ORDER WHERE MEM_ID = ?";
	@Override
	public void insert(SouvenirOrderVO soVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, soVO.getEmp_id());
			pstmt.setString(2, soVO.getMem_id());
			pstmt.setString(3, soVO.getSou_receiver_name());
			pstmt.setString(4, soVO.getSou_receiver_address());
			pstmt.setString(5, soVO.getSou_receiver_phone());
			pstmt.setInt(6, soVO.getSou_shipment_fee());
			pstmt.setInt(7, soVO.getSou_order_sum_price());
			pstmt.setString(8, soVO.getSou_order_remarks());
			pstmt.setInt(9, soVO.getSou_shipping_method());
			pstmt.setInt(10, soVO.getSou_order_status());
			pstmt.setInt(11, soVO.getSou_payment_status());
			pstmt.setInt(12, soVO.getSou_shipment_status());

			pstmt.executeUpdate();
			System.out.println("新增成功 insert method");

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(String sou_order_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, sou_order_id);

			pstmt.executeUpdate();
			System.out.println("刪除成功 delete method");
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public SouvenirOrderVO findByPrimaryKey(String sou_order_id) {
		// TODO Auto-generated method stub
		SouvenirOrderVO soVO = null;
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
				// soVO 也稱為 Domain objects
				soVO = new SouvenirOrderVO();
				soVO.setSou_order_id(rs.getString("sou_order_id"));
				soVO.setEmp_id(rs.getString("emp_id"));
				soVO.setMem_id(rs.getString("mem_id"));
				soVO.setSou_order_date(rs.getTimestamp("sou_order_date"));
				soVO.setSou_receiver_name(rs.getString("sou_receiver_name"));
				soVO.setSou_receiver_address(rs.getString("sou_receiver_address"));
				soVO.setSou_receiver_phone(rs.getString("sou_receiver_phone"));
				soVO.setSou_shipment_fee(rs.getInt("sou_shipment_fee"));
				soVO.setSou_order_sum_price(rs.getInt("sou_order_sum_price"));
				soVO.setSou_order_remarks(rs.getString("sou_order_remarks"));
				soVO.setSou_shipping_method(rs.getInt("sou_shipping_method"));
				soVO.setSou_order_status(rs.getInt("sou_order_status"));
				soVO.setSou_payment_status(rs.getInt("sou_payment_status"));
				soVO.setSou_shipment_status(rs.getInt("sou_shipment_status"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return soVO;
	}

	@Override
	public void update(SouvenirOrderVO soVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

//			pstmt.setString(1, soVO.getEmp_id());
//			pstmt.setString(2, soVO.getMem_id());
//			pstmt.setTimestamp(3, soVO.getSou_order_date());
//			pstmt.setString(4, soVO.getSou_receiver_name());
//			pstmt.setString(5, soVO.getSou_receiver_address());
//			pstmt.setString(6, soVO.getSou_receiver_phone());
//			pstmt.setInt(7, soVO.getSou_shipment_fee());
//			pstmt.setInt(8, soVO.getSou_order_sum_price());
//			pstmt.setString(9, soVO.getSou_order_remarks());
//			pstmt.setInt(10, soVO.getSou_shipping_method());
//			pstmt.setInt(11, soVO.getSou_order_status());
//			pstmt.setInt(12, soVO.getSou_payment_status());
//			pstmt.setInt(13, soVO.getSou_shipment_status());
//			pstmt.setString(14, soVO.getSou_order_id());
//			
			//current版本

			pstmt.setString(1, soVO.getSou_receiver_name());
			pstmt.setString(2, soVO.getSou_receiver_address());
			pstmt.setString(3, soVO.getSou_receiver_phone());
			pstmt.setInt(4, soVO.getSou_shipment_fee());
			pstmt.setInt(5, soVO.getSou_order_sum_price());
			pstmt.setString(6, soVO.getSou_order_remarks());
			pstmt.setInt(7, soVO.getSou_shipping_method());
			pstmt.setInt(8, soVO.getSou_order_status());
			pstmt.setInt(9, soVO.getSou_payment_status());
			pstmt.setInt(10, soVO.getSou_shipment_status());
			pstmt.setString(11, soVO.getSou_order_id());

			pstmt.executeUpdate();
			System.out.println("更改成功 delete method");
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<SouvenirOrderVO> getAll() {
		// TODO Auto-generated method stub
		List<SouvenirOrderVO> list = new ArrayList<SouvenirOrderVO>();
		SouvenirOrderVO soVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// soVO 也稱為 Domain objects
				soVO = new SouvenirOrderVO();
				soVO.setSou_order_id(rs.getString("sou_order_id"));
				soVO.setEmp_id(rs.getString("emp_id"));
				soVO.setMem_id(rs.getString("mem_id"));
				soVO.setSou_order_date(rs.getTimestamp("sou_order_date"));
				soVO.setSou_receiver_name(rs.getString("sou_receiver_name"));
				soVO.setSou_receiver_address(rs.getString("sou_receiver_address"));
				soVO.setSou_receiver_phone(rs.getString("sou_receiver_phone"));
				soVO.setSou_shipment_fee(rs.getInt("sou_shipment_fee"));
				soVO.setSou_order_sum_price(rs.getInt("sou_order_sum_price"));
				soVO.setSou_order_remarks(rs.getString("sou_order_remarks"));
				soVO.setSou_shipping_method(rs.getInt("sou_shipping_method"));
				soVO.setSou_order_status(rs.getInt("sou_order_status"));
				soVO.setSou_payment_status(rs.getInt("sou_payment_status"));
				soVO.setSou_shipment_status(rs.getInt("sou_shipment_status"));
				list.add(soVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<SouvenirOrderVO> getAll(Map<String, String[]> map) {
		List<SouvenirOrderVO> list = new ArrayList<SouvenirOrderVO>();
		SouvenirOrderVO soVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String finalSQL = "select * from SOUVENIR_ORDER "
		          + jdbcUtil_CompositeQuery_SouvenirOrder.get_WhereCondition(map)
		          + "order by sou_order_id";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				soVO = new SouvenirOrderVO();
				soVO.setSou_order_id(rs.getString("sou_order_id"));
				soVO.setEmp_id(rs.getString("emp_id"));
				soVO.setMem_id(rs.getString("mem_id"));
				soVO.setSou_order_date(rs.getTimestamp("sou_order_date"));
				soVO.setSou_receiver_name(rs.getString("sou_receiver_name"));
				soVO.setSou_receiver_address(rs.getString("sou_receiver_address"));
				soVO.setSou_receiver_phone(rs.getString("sou_receiver_phone"));
				soVO.setSou_shipment_fee(rs.getInt("sou_shipment_fee"));
				soVO.setSou_order_sum_price(rs.getInt("sou_order_sum_price"));
				soVO.setSou_order_remarks(rs.getString("sou_order_remarks"));
				soVO.setSou_shipping_method(rs.getInt("sou_shipping_method"));
				soVO.setSou_order_status(rs.getInt("sou_order_status"));
				soVO.setSou_payment_status(rs.getInt("sou_payment_status"));
				soVO.setSou_shipment_status(rs.getInt("sou_shipment_status"));
				list.add(soVO); // Store the row in the list
			}
	
			// Handle any SQL errors
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	 public List<SouvenirOrderVO> getSouvenirOrderByMemid(String mem_id){
		List<SouvenirOrderVO> list = new ArrayList<SouvenirOrderVO>();
		SouvenirOrderVO soVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;


		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_MEM_ID_STMT);
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();

				while (rs.next()) {
					soVO = new SouvenirOrderVO();
					soVO.setSou_order_id(rs.getString("sou_order_id"));
					soVO.setEmp_id(rs.getString("emp_id"));
					soVO.setMem_id(rs.getString("mem_id"));
					soVO.setSou_order_date(rs.getTimestamp("sou_order_date"));
					soVO.setSou_receiver_name(rs.getString("sou_receiver_name"));
					soVO.setSou_receiver_address(rs.getString("sou_receiver_address"));
					soVO.setSou_receiver_phone(rs.getString("sou_receiver_phone"));
					soVO.setSou_shipment_fee(rs.getInt("sou_shipment_fee"));
					soVO.setSou_order_sum_price(rs.getInt("sou_order_sum_price"));
					soVO.setSou_order_remarks(rs.getString("sou_order_remarks"));
					soVO.setSou_shipping_method(rs.getInt("sou_shipping_method"));
					soVO.setSou_order_status(rs.getInt("sou_order_status"));
					soVO.setSou_payment_status(rs.getInt("sou_payment_status"));
					soVO.setSou_shipment_status(rs.getInt("sou_shipment_status"));
					list.add(soVO); // Store the row in the list
				}
				
			}catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
				// Handle any SQL errors
			// Handle any driver errors
		} catch (SQLException se) {
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
	public void insertWithDetail( SouvenirOrderVO soVO , List<SouvenirOrderDetailVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
			
    		// 先新增訂單編號
			String cols[] = {"SOU_ORDER_ID"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);			
			pstmt.setString(1, soVO.getEmp_id());
			pstmt.setString(2, soVO.getMem_id());
			pstmt.setString(3, soVO.getSou_receiver_name());
			pstmt.setString(4, soVO.getSou_receiver_address());
			pstmt.setString(5, soVO.getSou_receiver_phone());
			pstmt.setInt(6, soVO.getSou_shipment_fee());
			pstmt.setInt(7, soVO.getSou_order_sum_price());
			pstmt.setString(8, soVO.getSou_order_remarks());
			pstmt.setInt(9, soVO.getSou_shipping_method());
			pstmt.setInt(10, soVO.getSou_order_status());
			pstmt.setInt(11, soVO.getSou_payment_status());
			pstmt.setInt(12, soVO.getSou_shipment_status());

			pstmt.executeUpdate();
			//掘取對應的自增主鍵值
			String next_sou_order_id = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_sou_order_id = rs.getString(1);
				System.out.println("自增主鍵值= " + next_sou_order_id +"(剛新增成功的特產編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增訂單明細
			SouvenirOrderDetailJDBCDAO dao = new SouvenirOrderDetailJDBCDAO();
			System.out.println("list.size()-A="+list.size());
			for (SouvenirOrderDetailVO aSOD : list) {
				aSOD.setSou_order_id(new String(next_sou_order_id)) ;
				dao.insert2(aSOD,con);
			}

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增訂單編號" + next_sou_order_id + "時,共有" + list.size()
					+ "產品明細同時被新增");
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-SOUVENIRORDER");
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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	public static void main(String[] args) {
		SouvenirOrderJDBCDAO dao = new SouvenirOrderJDBCDAO();
		// 新增
//		SouvenirOrderVO soVO1 = new SouvenirOrderVO();
//		soVO1.setEmp_id("EMP002");
//		soVO1.setMem_id("MEM004");
//		soVO1.setSou_receiver_name("信彰");
//		soVO1.setSou_receiver_address("桃園市八德區");
//		soVO1.setSou_receiver_phone("0988866111");
//		soVO1.setSou_shipment_fee(60);
//		soVO1.setSou_order_sum_price(3980);
//		soVO1.setSou_order_remarks("包裝");
//		soVO1.setSou_shipping_method(0);
//		soVO1.setSou_order_status(0);
//		soVO1.setSou_payment_status(0);
//		soVO1.setSou_shipment_status(0);
//		dao.insert(soVO1);
		// 修改
//		SouvenirOrderVO soVO2 = new SouvenirOrderVO();
//		soVO2.setEmp_id("EMP002");
//		soVO2.setMem_id("MEM004");
////		soVO2.setSou_order_date(java.sql.Timestamp.valueOf("2021-01-02 23:39:44"));
//		soVO2.setSou_receiver_name("信彰");
//		soVO2.setSou_receiver_address("桃園市八德區");
//		soVO2.setSou_receiver_phone("0988866111");
//		soVO2.setSou_shipment_fee(60);
//		soVO2.setSou_order_sum_price(3980);
//		soVO2.setSou_order_remarks("卸貨");
//		soVO2.setSou_shipping_method(0);
//		soVO2.setSou_order_status(0);
//		soVO2.setSou_payment_status(0);
//		soVO2.setSou_shipment_status(0);
//		soVO2.setSou_order_id("SO008");
//		dao.update(soVO2);
		// 查詢
//		List<SouvenirOrderVO> list = dao.getAll();
//		for (SouvenirOrderVO aSo : list) {
//			System.out.print(aSo.getSou_order_id() + ",");
//			System.out.print(aSo.getEmp_id() + ",");
//			System.out.print(aSo.getMem_id() + ",");
//			System.out.print(aSo.getSou_order_date() + ",");
//			System.out.print(aSo.getSou_receiver_name() + ",");
//			System.out.print(aSo.getSou_receiver_address() + ",");
//			System.out.print(aSo.getSou_receiver_phone() + ",");
//			System.out.print(aSo.getSou_shipment_fee() + ",");
//			System.out.print(aSo.getSou_order_sum_price() + ",");
//			System.out.print(aSo.getSou_order_remarks() + ",");
//			System.out.print(aSo.getSou_shipping_method() + ",");
//			System.out.print(aSo.getSou_order_status() + ",");
//			System.out.print(aSo.getSou_payment_status() + ",");
//			System.out.println(aSo.getSou_shipment_status() + ",");
//
//		}
		// 刪除
//		dao.delete("SO006");
		// 單一查詢
//		SouvenirOrderVO so3 = dao.findByPrimaryKey("SO020");
//		System.out.print(so3.getSou_order_id() + ",");
//		System.out.print(so3.getEmp_id() + ",");
//		System.out.print(so3.getMem_id() + ",");
//		System.out.print(so3.getSou_order_date() + ",");
//		System.out.print(so3.getSou_receiver_name() + ",");
//		System.out.print(so3.getSou_receiver_address() + ",");
//		System.out.print(so3.getSou_receiver_phone() + ",");
//		System.out.print(so3.getSou_shipment_fee() + ",");
//		System.out.print(so3.getSou_order_sum_price() + ",");
//		System.out.print(so3.getSou_order_remarks() + ",");
//		System.out.print(so3.getSou_shipping_method() + ",");
//		System.out.print(so3.getSou_order_status() + ",");
//		System.out.print(so3.getSou_payment_status() + ",");
//		System.out.print(so3.getSou_shipment_status() + ",");
		
		SouvenirOrderVO soVO = new SouvenirOrderVO();
		soVO.setEmp_id("EMP002");
		soVO.setMem_id("MEM004");
		soVO.setSou_receiver_name("信彰");
		soVO.setSou_receiver_address("桃園市八德區");
		soVO.setSou_receiver_phone("0988866111");
		soVO.setSou_shipment_fee(60);
		soVO.setSou_order_sum_price(3980);
		soVO.setSou_order_remarks("包裝");
		soVO.setSou_shipping_method(0);
		soVO.setSou_order_status(0);
		soVO.setSou_payment_status(0);
		soVO.setSou_shipment_status(0);
		
		List<SouvenirOrderDetailVO> testList = new ArrayList<SouvenirOrderDetailVO>(); 
		SouvenirOrderDetailVO sodXX = new SouvenirOrderDetailVO(); 
		sodXX.setSou_id("SOU002");
		sodXX.setSou_order_amount(30);
		sodXX.setSou_price(40);
	
		SouvenirOrderDetailVO sodYY = new SouvenirOrderDetailVO();  
		sodYY.setSou_id("SOU001");
		sodYY.setSou_order_amount(10);
		sodYY.setSou_price(400);

		testList.add(sodXX);
		testList.add(sodYY);
		
		dao.insertWithDetail(soVO , testList);
	}
}
