package com.activity_photo.model;

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

public class ActivityPhotoDAO implements ActivityPhotoDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO ACTIVITY_PHOTO(ACT_PHOTO_ID, ACT_ID, ACT_PHOTO, ACT_PHOTO_CONTENT)" + 
			"VALUES('ACTPHO' || LPAD(ACTPHO_SEQ.NEXTVAL, 3, '0'), ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT ACT_PHOTO_ID,ACT_ID, ACT_PHOTO, ACT_PHOTO_CONTENT FROM ACTIVITY_PHOTO";
	private static final String GET_ONE_STMT = "SELECT ACT_PHOTO_ID,ACT_ID, ACT_PHOTO, ACT_PHOTO_CONTENT FROM ACTIVITY_PHOTO where ACT_PHOTO_ID = ?";
//	private static final String GET_Mem_ByDeptno_STMT = "SELECT empno,ename,job,to_char(hiredate,'yyyy-mm-dd') hiredate,sal,comm,deptno FROM emp2 where deptno = ? order by empno";
//	
//	private static final String DELETE_ACTIVITY_TYPE = "DELETE FROM ACTIVITY_TYPE where deptno = ?";
	private static final String DELETE = "DELETE FROM ACTIVITY_PHOTO where ACT_PHOTO_ID = ?";	

	private static final String UPDATE = "UPDATE ACTIVITY_PHOTO set ACT_ID=?, ACT_PHOTO=?, ACT_PHOTO_CONTENT=? where ACT_PHOTO_ID=?";
	private static final String UPDATE_PHOTO_BY_ACT_PHOTO_ID = "UPDATE ACTIVITY_PHOTO set ACT_PHOTO=? where ACT_PHOTO_ID=?";
	private static final String GET_PHOTO_BY_ACT_ID="SELECT ACT_PHOTO_ID,ACT_ID, ACT_PHOTO, ACT_PHOTO_CONTENT FROM ACTIVITY_PHOTO where ACT_ID=?";
	@Override
	public void update_photo_by_act_photo_id(ActivityPhotoVO actphoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PHOTO_BY_ACT_PHOTO_ID);

			pstmt.setBytes(1, actphoVO.getAct_photo());
			pstmt.setString(2, actphoVO.getAct_photo_id());
			
			
			
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
	public void insert(ActivityPhotoVO actphoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, actphoVO.getAct_id());
			pstmt.setBytes(2, actphoVO.getAct_photo());
			pstmt.setString(3, actphoVO.getAct_photo_content());
//			
			
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
	public void update(ActivityPhotoVO actphoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, actphoVO.getAct_id());
			pstmt.setBytes(2, actphoVO.getAct_photo());
			pstmt.setString(3, actphoVO.getAct_photo_content());
			pstmt.setString(4, actphoVO.getAct_photo_id());
			
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
	public ActivityPhotoVO findByPrimaryKey(String act_photo_id) {
		ActivityPhotoVO actphoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, act_photo_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				actphoVO = new ActivityPhotoVO();
				actphoVO.setAct_photo_id(rs.getString("ACT_PHOTO_ID"));
				actphoVO.setAct_id(rs.getString("ACT_ID"));
				actphoVO.setAct_photo(rs.getBytes("ACT_PHOTO"));
				actphoVO.setAct_photo_content(rs.getString("ACT_PHOTO_CONTENT"));
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
		return actphoVO;
	}

	@Override
	public void delete(String act_photo_id) { 
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, act_photo_id);
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<ActivityPhotoVO> getAll() {
		List<ActivityPhotoVO>list=new ArrayList<ActivityPhotoVO>();
		ActivityPhotoVO actphoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				actphoVO = new ActivityPhotoVO();
				actphoVO.setAct_photo_id(rs.getString("ACT_PHOTO_ID"));
				actphoVO.setAct_id(rs.getString("ACT_ID"));
				actphoVO.setAct_photo(rs.getBytes("ACT_PHOTO"));
				actphoVO.setAct_photo_content(rs.getString("ACT_PHOTO_CONTENT"));
				list.add(actphoVO);
			}
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
	public List<ActivityPhotoVO> getActivityPhotoByActId(String act_id) {
		List<ActivityPhotoVO>list=new ArrayList<ActivityPhotoVO>();
		ActivityPhotoVO actphoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PHOTO_BY_ACT_ID);
			pstmt.setString(1, act_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				actphoVO = new ActivityPhotoVO();
				actphoVO.setAct_photo_id(rs.getString("ACT_PHOTO_ID"));
				actphoVO.setAct_id(rs.getString("ACT_ID"));
				actphoVO.setAct_photo(rs.getBytes("ACT_PHOTO"));
				actphoVO.setAct_photo_content(rs.getString("ACT_PHOTO_CONTENT"));
				list.add(actphoVO);
			}
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
		ActivityPhotoJDBCDAO dao=new ActivityPhotoJDBCDAO();
		//新增=======================================================
//		ActivityPhotoVO actphoVO = new ActivityPhotoVO();
//		actphoVO.setAct_id("ACT001");
//		actphoVO.setAct_photo_content("傑哥天體營 歡迎大家加入");
//		dao.insert(actphoVO);
		//修改 update================================================
//		ActivityPhotoVO actphoVO2 = new ActivityPhotoVO();
//		actphoVO2.setAct_id("ACT001");
//		actphoVO2.setAct_photo_content("傑哥天體營 歡迎大家加入傑哥天體營 歡迎大家加入");
//		actphoVO2.setAct_photo_id("ACTPHO001");
//		dao.update(actphoVO2);
//		
		//刪除 delete=================================================
//		dao.delete("ACTPHO005");
		//查詢 getByPK================================================
		ActivityPhotoVO actphoVO3 = dao.findByPrimaryKey("ACTPHO001");
		System.out.println(actphoVO3.getAct_photo_id());
		System.out.println(actphoVO3.getAct_id());
		System.out.println(actphoVO3.getAct_photo());
		System.out.println(actphoVO3.getAct_photo_content());
		//查詢 getAll=================================================
//		List<ActivityPhotoVO> list=dao.getAll();
//		for(ActivityPhotoVO aActPho:list) {
//			System.out.println(aActPho.getAct_photo_id());
//			System.out.println(aActPho.getAct_id());
//			System.out.println(aActPho.getAct_photo());
//			System.out.println(aActPho.getAct_photo_content());
//			System.out.println("==============================");
//		}
		
	}



}
