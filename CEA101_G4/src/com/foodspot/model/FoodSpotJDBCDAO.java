package com.foodspot.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodSpotJDBCDAO implements FoodSpotDAO_interface {
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String userid = "CEA101G4";
	private static final String passwd = "CEA101G4";
	
	private static final String INSERT_STMT =
			"INSERT INTO FOOD_SPOT(FAS_ID, SELL_MEM_ID, FAS_SPOT_NAME, FAS_ADD, FAS_DES, FAS_PHOTO, FAS_LATITUDE, FAS_LONGITUD)"
			+ " VALUES ('FAS' || LPAD(FAS_SEQ.NEXTVAL, 3, '0'),?,?,?,?,?,?,?)";
	private static final String UPDATE = 
			"UPDATE FOOD_SPOT set SELL_MEM_ID=?,FAS_SPOT_NAME=?,FAS_ADD=?,FAS_DES=?, FAS_PHOTO=?, FAS_LATITUDE=?,FAS_LONGITUD=? where FAS_ID=?";
	private static final String DELETE = 
			"DELETE FROM FOOD_SPOT WHERE FAS_ID = ?";
	private static final String GET_BY_SELL =
			"SELECT * FROM FOOD_SPOT WHERE SELL_MEM_ID = ?";
	private static final String GET_ONE_STMT =
			"SELECT FAS_ID, SELL_MEM_ID, FAS_SPOT_NAME, FAS_ADD, FAS_DES, FAS_PHOTO, FAS_LATITUDE, FAS_LONGITUD FROM FOOD_SPOT where FAS_ID = ?";
	private static final String GET_ALL_STMT =
			"SELECT FAS_ID, SELL_MEM_ID, FAS_SPOT_NAME, FAS_ADD, FAS_DES, FAS_PHOTO, FAS_LATITUDE, FAS_LONGITUD FROM FOOD_SPOT order by FAS_ID";
//	private static final String GET_ONE_FAS_PHOTO =
//			"SELECT FAS_PHOTO FROM Food_Spot where FAS_ID=?";
	@Override
	public void insert(FoodSpotVO fsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			
			pstmt.setString(1, fsVO.getSell_mem_id());
			pstmt.setString(2, fsVO.getFas_spot_name());
			pstmt.setString(3, fsVO.getFas_add());
			pstmt.setString(4, fsVO.getFas_des());
			pstmt.setBytes(5, fsVO.getFas_photo());
			pstmt.setDouble(6, fsVO.getFas_latitude());
			pstmt.setDouble(7, fsVO.getFas_longitud());
			
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
	public void update(FoodSpotVO fsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, fsVO.getSell_mem_id());
			pstmt.setString(2, fsVO.getFas_spot_name());
			pstmt.setString(3, fsVO.getFas_add());
			pstmt.setString(4, fsVO.getFas_des());
			pstmt.setBytes(5, fsVO.getFas_photo());
			pstmt.setDouble(6, fsVO.getFas_latitude());
			pstmt.setDouble(7, fsVO.getFas_longitud());
			pstmt.setString(8, fsVO.getFas_id());
			

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
	public void delete(String fas_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1,fas_id);

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
	public FoodSpotVO findByPrimaryKey(String fas_id) {

		FoodSpotVO fsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, fas_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				fsVO = new FoodSpotVO();
				fsVO.setFas_id(rs.getString("fas_id"));
				fsVO.setSell_mem_id(rs.getString("sell_mem_id"));
				fsVO.setFas_spot_name(rs.getString("fas_spot_name"));
				fsVO.setFas_add(rs.getString("fas_add"));
				fsVO.setFas_des(rs.getString("fas_des"));
				fsVO.setFas_photo(rs.getBytes("fas_photo"));
				fsVO.setFas_latitude(rs.getDouble("fas_latitude"));
				fsVO.setFas_longitud(rs.getDouble("fas_longitud"));
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
		return fsVO;
	}
	 
	@Override
	public List<FoodSpotVO> findBySell(String sell_mem_id) {
		List<FoodSpotVO> list = new ArrayList<FoodSpotVO>();
		FoodSpotVO fsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_SELL);

			pstmt.setString(1, sell_mem_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				fsVO = new FoodSpotVO();		
				fsVO.setFas_id(rs.getString("fas_id"));		
				fsVO.setSell_mem_id(rs.getString("sell_mem_id"));
				fsVO.setFas_spot_name(rs.getString("fas_spot_name"));
				fsVO.setFas_add(rs.getString("fas_add"));
				fsVO.setFas_des(rs.getString("fas_des"));
				fsVO.setFas_photo(rs.getBytes("fas_photo"));
				fsVO.setFas_latitude(rs.getDouble("fas_latitude"));
				fsVO.setFas_longitud(rs.getDouble("fas_longitud"));
				list.add(fsVO);
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

	@Override
	public List<FoodSpotVO> getAll() {
		List<FoodSpotVO> list = new ArrayList<FoodSpotVO>();
		FoodSpotVO fsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				fsVO = new FoodSpotVO();
				fsVO.setFas_id(rs.getString("fas_id"));
				fsVO.setSell_mem_id(rs.getString("sell_mem_id"));
				fsVO.setFas_spot_name(rs.getString("fas_spot_name"));
				fsVO.setFas_add(rs.getString("fas_add"));
				fsVO.setFas_des(rs.getString("fas_des"));
				fsVO.setFas_photo(rs.getBytes("fas_photo"));
				fsVO.setFas_latitude(rs.getDouble("fas_latitude"));
				fsVO.setFas_longitud(rs.getDouble("fas_longitud"));
				list.add(fsVO);
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
	
	
//	@Override
//	public FoodSpotVO getFasPhoto(String fas_id) {
//		FoodSpotVO fsVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ONE_FAS_PHOTO);
//
//			pstmt.setString(1, fas_id);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//			
//				fsVO = new FoodSpotVO();
//				
//				fsVO.setFas_photo(rs.getBytes("fas_photo"));
//				
//			}
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		
//		return fsVO;
//	}

	public static void main(String[] args) throws IOException {

		FoodSpotJDBCDAO dao = new FoodSpotJDBCDAO();

		// 新增
//		FoodSpotVO fsVO1 = new FoodSpotVO();
//		fsVO1.setSell_mem_id("SELL004");
//		fsVO1.setFas_spot_name("溫刀");
//		fsVO1.setFas_add("桃園市八德區何神路100號");
//		fsVO1.setFas_des("家徒四壁");
//		fsVO1.setFas_photo(getPicByteArray());
//		fsVO1.setFas_latitude(22.9576852);
//		fsVO1.setFas_longitud(121.3829382);
//		
//		dao.insert(fsVO1);
	

		// 修改
//		FoodSpotVO fsVO2 = new FoodSpotVO();
//		fsVO2.setSell_mem_id("SELL002");
//		fsVO2.setFas_spot_name("傑哥家");
//		fsVO2.setFas_add("嘉義市東區傳傑路100號");
//		fsVO2.setFas_des("金碧輝煌");
//		fsVO2.setFas_photo(getPicByteArray());
//		fsVO2.setFas_latitude(22.9576852);
//		fsVO2.setFas_longitud(121.3829382);
//		fsVO2.setFas_id("FAS001");
		
				
//		dao.update(fsVO2);
	

		// 刪除
//		dao.delete("FAS001");

		// 查詢
//		FoodSpotVO fsVO3 = dao.findByPrimaryKey("FAS002");
//		System.out.print(fsVO3.getFas_id() + ",");
//		System.out.print(fsVO3.getSell_mem_id() + ",");
//		System.out.print(fsVO3.getFas_spot_name() + ",");
//		System.out.print(fsVO3.getFas_add() + ",");
//		System.out.print(fsVO3.getFas_des() + ",");
//		System.out.print(fsVO3.getFas_photo() + ",");
//		System.out.print(fsVO3.getFas_latitude() + ",");
//		System.out.print(fsVO3.getFas_longitud() + ",");
//		
//		System.out.println("");
//		System.out.println("---------------------");
	
//
//		// 查詢
		List<FoodSpotVO> list = dao.getAll();
		for (FoodSpotVO fsVO4 : list) {
			System.out.print(fsVO4.getFas_id() + ",");
			System.out.print(fsVO4.getSell_mem_id() + ",");
			System.out.print(fsVO4.getFas_spot_name() + ",");
			System.out.print(fsVO4.getFas_add() + ",");
			System.out.print(fsVO4.getFas_des() + ",");
		    System.out.print(fsVO4.getFas_photo() + ",");
			System.out.print(fsVO4.getFas_latitude() + ",");
			System.out.print(fsVO4.getFas_longitud() + ",");
			System.out.println();
		}
	}
	
		public static byte[] getPicByteArray() throws IOException{
		File file = new File("Webcontent/front-end/images/p3.png");
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
			baos.flush();
		}
		baos.close();
		fis.close();
		
		return baos.toByteArray();
		
	}
	 
	
	public static void readPic(byte[] bytes) throws IOException {
		FileOutputStream fos = new FileOutputStream("picture");
		fos.write(bytes);
		fos.flush();
		fos.close();
	}
	
}
