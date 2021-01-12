package com.activity_product.model;

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

public class ActivityProductDAO implements ActivityProductDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO ACTIVITY_PRODUCT(ACT_ID, SELL_MEM_ID, ACT_TYPE_ID, ACT_NAME, ACT_PRICE,ACT_DES,ACT_ADD)" + 
			"VALUES('ACT' || LPAD(ACT_SEQ.NEXTVAL, 3, '0'), ?,?, ?, ?, ?,?)";
	private static final String GET_ALL_STMT = "SELECT ACT_ID, SELL_MEM_ID, ACT_TYPE_ID, ACT_NAME, ACT_PRICE,ACT_DES,ACT_ADD FROM ACTIVITY_PRODUCT";
	private static final String GET_ONE_STMT = "SELECT ACT_ID,SELL_MEM_ID, ACT_TYPE_ID, ACT_NAME, ACT_PRICE,ACT_DES,ACT_ADD FROM ACTIVITY_PRODUCT where ACT_ID = ?";
	private static final String GET_ALL_BY_SELL_MEM_ID = "SELECT ACT_ID,SELL_MEM_ID, ACT_TYPE_ID, ACT_NAME, ACT_PRICE,ACT_DES,ACT_ADD FROM ACTIVITY_PRODUCT where SELL_MEM_ID = ?";
//	private static final String GET_Mem_ByDeptno_STMT = "SELECT empno,ename,job,to_char(hiredate,'yyyy-mm-dd') hiredate,sal,comm,deptno FROM emp2 where deptno = ? order by empno";
//	
//	private static final String DELETE_ACTIVITY_TYPE = "DELETE FROM ACTIVITY_TYPE where deptno = ?";
	private static final String DELETE = "DELETE FROM ACTIVITY_PRODUCT where ACT_ID = ?";	

	private static final String UPDATE = "UPDATE ACTIVITY_PRODUCT set SELL_MEM_ID =?, ACT_TYPE_ID=?, ACT_NAME=?, ACT_PRICE=?,ACT_DES=?,ACT_ADD=? where ACT_ID=?";
	@Override
	public void insert(ActivityProductVO actpVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, actpVO.getSell_mem_id());
			pstmt.setString(2, actpVO.getAct_type_id());
			pstmt.setString(3, actpVO.getAct_name());
			pstmt.setDouble(4, actpVO.getAct_price());
			pstmt.setString(5,actpVO.getAct_des());
//			Reader reader = getLongStringStream(actVO.getIntropath());
//			pstmt.setCharacterStream(5, reader);
			pstmt.setString(6, actpVO.getAct_add());
			
			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void update(ActivityProductVO actpVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, actpVO.getSell_mem_id());
			pstmt.setString(2, actpVO.getAct_type_id());
			pstmt.setString(3, actpVO.getAct_name());
			pstmt.setDouble(4, actpVO.getAct_price());
			pstmt.setString(5, actpVO.getAct_des());
			pstmt.setString(6, actpVO.getAct_add());
			pstmt.setString(7, actpVO.getAct_id());
			
			pstmt.executeUpdate();

			// Handle any driver errors
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
	public ActivityProductVO findByPrimaryKey(String act_id) {
		ActivityProductVO actpVO=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, act_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				actpVO = new ActivityProductVO();
				actpVO.setAct_id(rs.getString("ACT_ID"));
				actpVO.setSell_mem_id(rs.getString("SELL_MEM_ID"));
				actpVO.setAct_type_id(rs.getString("ACT_TYPE_ID"));
				actpVO.setAct_name(rs.getString("ACT_NAME"));
				actpVO.setAct_price(rs.getDouble("ACT_PRICE"));
				actpVO.setAct_des(rs.getString("ACT_DES"));
				actpVO.setAct_add(rs.getString("ACT_ADD"));
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
		return actpVO;
	}

	@Override
	public void delete(String act_id) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<ActivityProductVO> getAllbySellMemId(String sell_mem_id) {
		List<ActivityProductVO> list = new ArrayList<ActivityProductVO>();
		ActivityProductVO actpVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_SELL_MEM_ID);
			pstmt.setString(1, sell_mem_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				actpVO = new ActivityProductVO();
				actpVO.setAct_id(rs.getString("ACT_ID"));
				actpVO.setSell_mem_id(rs.getString("SELL_MEM_ID"));
				actpVO.setAct_type_id(rs.getString("ACT_TYPE_ID"));
				actpVO.setAct_name(rs.getString("ACT_NAME"));
				actpVO.setAct_price(rs.getDouble("ACT_PRICE"));
				actpVO.setAct_des(rs.getString("ACT_DES"));
				actpVO.setAct_add(rs.getString("ACT_ADD"));
				list.add(actpVO); // Store the row in the list
			}

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
	public List<ActivityProductVO> getAll() {
		List<ActivityProductVO> list = new ArrayList<ActivityProductVO>();
		ActivityProductVO actpVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				actpVO = new ActivityProductVO();
				actpVO.setAct_id(rs.getString("ACT_ID"));
				actpVO.setSell_mem_id(rs.getString("SELL_MEM_ID"));
				actpVO.setAct_type_id(rs.getString("ACT_TYPE_ID"));
				actpVO.setAct_name(rs.getString("ACT_NAME"));
				actpVO.setAct_price(rs.getDouble("ACT_PRICE"));
				actpVO.setAct_des(rs.getString("ACT_DES"));
				actpVO.setAct_add(rs.getString("ACT_ADD"));
				list.add(actpVO); // Store the row in the list
			}

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

	public static void main(String[] args) {
		ActivityProductJDBCDAO dao=new ActivityProductJDBCDAO();
		//新增=======================================================
		for(int i=0;i<30;i++) {
			
			ActivityProductVO actpVO = new ActivityProductVO();
			actpVO.setSell_mem_id("SELL001");
			actpVO.setAct_type_id("AT001");
			actpVO.setAct_name("單戀不算失戀"+i);
			actpVO.setAct_price(2880D);
			actpVO.setAct_des("大家都愛哭"+i);
			actpVO.setAct_add("富士山左轉"+i);
			
			dao.insert(actpVO);
		}
		//修改 update================================================
//		ActivityProductVO actpVO2 = new ActivityProductVO();
//		actpVO2.setSell_mem_id("SELL001");
//		actpVO2.setAct_type_id("AT001");
//		actpVO2.setAct_name("單戀不算失戀");
//		actpVO2.setAct_price(2880D);
//		actpVO2.setAct_add("富士山左轉");
//		actpVO2.setAct_id("ACT003");
//		dao.update(actpVO2);
		//查詢 getAll================================================
//		List<ActivityProductVO> list = dao.getAll();
//		for (ActivityProductVO aActp : list) {
//			System.out.print(aActp.getAct_id() + ",");
//			System.out.print(aActp.getSell_mem_id() + ",");
//			System.out.print(aActp.getAct_type_id() + ",");
//			System.out.print(aActp.getAct_name() + ",");
//			System.out.print(aActp.getAct_price() + ",");
//			System.out.print(aActp.getAct_des() + ",");
//			System.out.print(aActp.getAct_add());
//			System.out.println();
//		}
		//查詢getByPK=============================================
//		ActivityProductVO actpVO3 = dao.findByPrimaryKey("ACT003");
//		System.out.print(actpVO3.getSell_mem_id() + ",");
//		System.out.print(actpVO3.getAct_type_id() + ",");
//		System.out.print(actpVO3.getAct_name() + ",");
//		System.out.print(actpVO3.getAct_price() + ",");
//		System.out.print(actpVO3.getAct_des() + ",");
//		System.out.print(actpVO3.getAct_add());
//		System.out.println();
//		System.out.println("---------------------");
		//查詢getBySell
//		List<ActivityProductVO> list = dao.getAllbySellMemId("SELL001");
//		for (ActivityProductVO aActp : list) {
//			System.out.print(aActp.getAct_id() + ",");
//			System.out.print(aActp.getSell_mem_id() + ",");
//			System.out.print(aActp.getAct_type_id() + ",");
//			System.out.print(aActp.getAct_name() + ",");
//			System.out.print(aActp.getAct_price() + ",");
//			System.out.print(aActp.getAct_des() + ",");
//			System.out.print(aActp.getAct_add());
//			System.out.println();
//		}
		
	}
	
	

	
	
	

}
