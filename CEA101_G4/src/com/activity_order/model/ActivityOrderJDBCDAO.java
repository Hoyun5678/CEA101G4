package com.activity_order.model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import util.Util;




public class ActivityOrderJDBCDAO implements ActivityOrderDAO_interface {
	String driver = Util.DRIVER;
	String url = Util.URL;
	String userid = Util.USER;
	String passwd = Util.PASSWORD;
	private static final String INSERT_STMT = "INSERT INTO ACTIVITY_ORDER(ACT_ORDER_ID,MEM_ID, ACT_PERIOD_ID,"
			+ "ACT_ORDER_AMOUNT, ACT_SUM_PRICE, ACT_ORDER_STATUS, ACT_PAYMENT_STATUS, ACT_ORDER_REMARKS)"
			+ "VALUES('AO' || LPAD(AO_SEQ.NEXTVAL, 3, '0'),?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT ACT_ORDER_ID,MEM_ID, ACT_PERIOD_ID," 
						+ "ACT_ORDER_AMOUNT, ACT_SUM_PRICE, ACT_ORDER_STATUS, ACT_PAYMENT_STATUS, ACT_ORDER_REMARKS FROM ACTIVITY_ORDER";
	private static final String GET_BY_MEM_ID_STMT = "SELECT ACT_ORDER_ID,MEM_ID, ACT_PERIOD_ID," 
			+ "ACT_ORDER_AMOUNT, ACT_SUM_PRICE, ACT_ORDER_STATUS, ACT_PAYMENT_STATUS, ACT_ORDER_REMARKS FROM ACTIVITY_ORDER where MEM_ID=?";
	private static final String GET_ONE_STMT = "SELECT ACT_ORDER_ID,MEM_ID, ACT_PERIOD_ID,"
			+ "ACT_ORDER_AMOUNT, ACT_SUM_PRICE, ACT_ORDER_STATUS, ACT_PAYMENT_STATUS, ACT_ORDER_REMARKS FROM ACTIVITY_ORDER where ACT_ORDER_ID = ?";
//	private static final String GET_Mem_ByDeptno_STMT = "SELECT empno,ename,job,to_char(hiredate,'yyyy-mm-dd') hiredate,sal,comm,deptno FROM emp2 where deptno = ? order by empno";
//	
//	private static final String DELETE_ACTIVITY_TYPE = "DELETE FROM ACTIVITY_TYPE where deptno = ?";
//	private static final String DELETE = "DELETE FROM ACTIVITY_PRODUCT where ACT_ID = ?";

	private static final String UPDATE = "UPDATE ACTIVITY_ORDER set MEM_ID=?, ACT_PERIOD_ID=?," + 
						"ACT_ORDER_AMOUNT=?, ACT_SUM_PRICE=?, ACT_ORDER_STATUS=?, ACT_PAYMENT_STATUS=?, ACT_ORDER_REMARKS=? where ACT_ORDER_ID=?";

	@Override
	public void insert(ActivityOrderVO actoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, actoVO.getMem_id());
			pstmt.setString(2, actoVO.getAct_period_id());
			pstmt.setInt(3, actoVO.getAct_order_amount());
			pstmt.setDouble(4, actoVO.getAct_sum_price());
			pstmt.setInt(5, actoVO.getAct_order_status());
			pstmt.setInt(6, actoVO.getAct_payment_status());
			pstmt.setString(7, actoVO.getAct_order_remarks());

			pstmt.executeUpdate();

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
	public void update(ActivityOrderVO actoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, actoVO.getMem_id());
			pstmt.setString(2, actoVO.getAct_period_id());
			pstmt.setInt(3, actoVO.getAct_order_amount());
			pstmt.setDouble(4, actoVO.getAct_sum_price());
			pstmt.setInt(5, actoVO.getAct_order_status());
			pstmt.setInt(6, actoVO.getAct_payment_status());
			pstmt.setString(7, actoVO.getAct_order_remarks());
			pstmt.setString(8, actoVO.getAct_order_id());
			pstmt.executeUpdate();

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
	public ActivityOrderVO findByPrimaryKey(String act_order_id) {
		ActivityOrderVO actoVO=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, act_order_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deptVO 也稱為 Domain objects
				actoVO = new ActivityOrderVO();
				actoVO.setAct_order_id(rs.getString("ACT_ORDER_ID"));
				actoVO.setMem_id(rs.getString("MEM_ID"));
				actoVO.setAct_period_id(rs.getString("ACT_PERIOD_ID"));
				actoVO.setAct_order_amount(rs.getInt("ACT_ORDER_AMOUNT"));
				actoVO.setAct_sum_price(rs.getDouble("ACT_SUM_PRICE"));
				actoVO.setAct_order_status(rs.getInt("ACT_ORDER_STATUS"));
				actoVO.setAct_payment_status(rs.getInt("ACT_PAYMENT_STATUS"));
				actoVO.setAct_order_remarks(rs.getString("ACT_ORDER_REMARKS"));
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
		return actoVO;
	}

	@Override
	public List<ActivityOrderVO> getAll() {
		List<ActivityOrderVO> list = new ArrayList<ActivityOrderVO>();
		ActivityOrderVO actoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				actoVO = new ActivityOrderVO();
				actoVO.setAct_order_id(rs.getString("ACT_ORDER_ID"));
				actoVO.setMem_id(rs.getString("MEM_ID"));
				actoVO.setAct_period_id(rs.getString("ACT_PERIOD_ID"));
				actoVO.setAct_order_amount(rs.getInt("ACT_ORDER_AMOUNT"));
				actoVO.setAct_sum_price(rs.getDouble("ACT_SUM_PRICE"));
				actoVO.setAct_order_status(rs.getInt("ACT_ORDER_STATUS"));
				actoVO.setAct_payment_status(rs.getInt("ACT_PAYMENT_STATUS"));
				actoVO.setAct_order_remarks(rs.getString("ACT_ORDER_REMARKS"));
				list.add(actoVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	public List<ActivityOrderVO> getActivityByMemid(String mem_id) {
		List<ActivityOrderVO> list = new ArrayList<ActivityOrderVO>();
		ActivityOrderVO actoVO = null;
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
				actoVO = new ActivityOrderVO();
				actoVO.setAct_order_id(rs.getString("ACT_ORDER_ID"));
				actoVO.setMem_id(rs.getString("MEM_ID"));
				actoVO.setAct_period_id(rs.getString("ACT_PERIOD_ID"));
				actoVO.setAct_order_amount(rs.getInt("ACT_ORDER_AMOUNT"));
				actoVO.setAct_sum_price(rs.getDouble("ACT_SUM_PRICE"));
				actoVO.setAct_order_status(rs.getInt("ACT_ORDER_STATUS"));
				actoVO.setAct_payment_status(rs.getInt("ACT_PAYMENT_STATUS"));
				actoVO.setAct_order_remarks(rs.getString("ACT_ORDER_REMARKS"));
				list.add(actoVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	public static void main(String[] args) {
		ActivityOrderJDBCDAO dao=new ActivityOrderJDBCDAO();
		// 新增 insert====================================
//		ActivityOrderVO actoVO = new ActivityOrderVO();
//		actoVO.setMem_id("MEM001");
//		actoVO.setAct_period_id("AP001");
//		actoVO.setAct_order_amount(5);
//		actoVO.setAct_sum_price(1800D);
//		actoVO.setAct_order_status(0);
//		actoVO.setAct_payment_status(0);
//		actoVO.setAct_order_remarks("想收集夏天的熱");
//		dao.insert(actoVO);
		//修改 update=====================================
//		ActivityOrderVO actoVO2=new ActivityOrderVO();
//		actoVO2.setMem_id("MEM001");
//		actoVO2.setAct_period_id("AP001");
//		actoVO2.setAct_order_amount(10);
//		actoVO2.setAct_sum_price(18800D);
//		actoVO2.setAct_order_status(2);
//		actoVO2.setAct_payment_status(3);
//		actoVO2.setAct_order_remarks("你是不是專題快不行了 ㄎㄎ");
//		actoVO2.setAct_order_id("AO001");
//		dao.update(actoVO2);
		//查詢 getAll====================================
//		List<ActivityOrderVO> list = dao.getAll();
//		for (ActivityOrderVO aActo : list) {
//			System.out.print(aActo.getAct_order_id() + ",");
//			System.out.print(aActo.getMem_id() + ",");
//			System.out.print(aActo.getAct_period_id() + ",");
//			System.out.print(aActo.getAct_order_amount() + ",");
//			System.out.print(aActo.getAct_sum_price() + ",");
//			System.out.print(aActo.getAct_order_status() + ",");
//			System.out.print(aActo.getAct_order_remarks());
//			System.out.println();
		//查詢 getByPK===================================
//		ActivityOrderVO actoVO3 = dao.findByPrimaryKey("AO003");
//		System.out.println(actoVO3.getMem_id() + ",");
//		System.out.println(actoVO3.getAct_period_id() + ",");
//		System.out.println(actoVO3.getAct_order_amount());
//		System.out.println(actoVO3.getAct_sum_price());
//		System.out.println(actoVO3.getAct_order_status());
//		System.out.println(actoVO3.getAct_payment_status());
//		System.out.println(actoVO3.getAct_order_remarks());
//		System.out.println("---------------------");
		}
		
		
	}

	





