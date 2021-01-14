package com.souvenir_photo.model;

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


import util.Util;



public class SouvenirPhotoJDBCDAO implements SouvenirPhotoDAO_interface{

	String driver = Util.DRIVER;
	String url = Util.URL;
	String userid = Util.USER;
	String passwd = Util.PASSWORD;

//	private static final String INSERT_STMT =
//			"INSERT INTO SOUVENIR_PHOTO(SOU_PHOTO_ID, SOU_ID, SOU_PHOTO,SOU_PHOTO_CONTENT)"
//			+ " VALUES ('SOUPH' || LPAD(SPHO_SEQ.NEXTVAL, 3, '0'),?,?,?)";
	private static final String UPDATE = 
 	"UPDATE SOUVENIR_PHOTO set SOU_ID=?, SOU_PHOTO=? where SOU_PHOTO_ID=?";
	private static final String DELETE = "DELETE FROM SOUVENIR_PHOTO where SOU_PHOTO_ID = ?";
	private static final String GET_ONE_STMT =
			"SELECT SOU_PHOTO_ID, SOU_ID, SOU_PHOTO FROM SOUVENIR_PHOTO where SOU_PHOTO_ID = ?";
	private static final String GET_ALL_STMT = 
			"SELECT SOU_PHOTO_ID, SOU_ID, SOU_PHOTO FROM SOUVENIR_PHOTO order by SOU_PHOTO_ID";
	private static final String GET_BYSOUID = 
			"SELECT * FROM SOUVENIR_PHOTO WHERE SOU_ID = ?";
	
	private static final String INSERT_STMT2 =
			"INSERT INTO SOUVENIR_PHOTO(SOU_PHOTO_ID, SOU_ID,SOU_PHOTO)"
			+ " VALUES ('SOUPH' || LPAD(SPHO_SEQ.NEXTVAL, 3, '0'),?,?)";

	@Override
	public void insert(SouvenirPhotoVO souphVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT2);

			pstmt.setBytes(2, souphVO.getSou_photo());
			pstmt.setString(1, souphVO.getSou_id());


			pstmt.executeUpdate();

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
	public void update(SouvenirPhotoVO souphVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, souphVO.getSou_id());
			pstmt.setBytes(2, souphVO.getSou_photo());
			pstmt.setString(4, souphVO.getSou_photo_id());

			

			pstmt.executeUpdate();

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
	public void delete(String getSou_photo_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, getSou_photo_id);
			pstmt.executeUpdate();

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
	public SouvenirPhotoVO findByPrimaryKey(String getSou_photo_id) {

		SouvenirPhotoVO souphVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, getSou_photo_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				souphVO = new SouvenirPhotoVO();
				
				souphVO.setSou_photo_id(rs.getString("sou_photo_id"));
				souphVO.setSou_id(rs.getString("sou_id"));
				souphVO.setSou_photo(rs.getBytes("sou_photo"));
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
		return souphVO;
	}

	@Override
	public List<SouvenirPhotoVO> getAll() {
		List<SouvenirPhotoVO> list = new ArrayList<SouvenirPhotoVO>();
		SouvenirPhotoVO souphVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				souphVO = new SouvenirPhotoVO();
				
				souphVO.setSou_photo_id(rs.getString("sou_photo_id"));
				souphVO.setSou_id(rs.getString("sou_id"));
				souphVO.setSou_photo(rs.getBytes("sou_photo"));
			
				list.add(souphVO);
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
	
	public List<SouvenirPhotoVO> getBySouId(String sou_id){
		List<SouvenirPhotoVO> list = new ArrayList<SouvenirPhotoVO>();

		SouvenirPhotoVO souphVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BYSOUID);
			pstmt.setString(1,sou_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				souphVO = new SouvenirPhotoVO();
				
				souphVO.setSou_photo_id(rs.getString("sou_photo_id"));
				souphVO.setSou_id(rs.getString("sou_id"));
				souphVO.setSou_photo(rs.getBytes("sou_photo"));
				list.add(souphVO);
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
	
	

	public static void main(String[] args) throws IOException {

		SouvenirPhotoJDBCDAO dao = new SouvenirPhotoJDBCDAO();
//		刪
//		dao.delete("SOUPH003");

//		增
//		SouvenirPhotoVO souphVO1 = new SouvenirPhotoVO();
//		souphVO1.setSou_id("SOU002");
//		souphVO1.setSou_photo(getPicByteArray());
//	
//		dao.insert(souphVO1);
		
		
		
//		改
//		SouvenirPhotoVO soupVO2 = new SouvenirPhotoVO();
//		soupVO2.setSou_photo_id("SOUPH002");
//		soupVO2.setSou_id("SOU003");
//		soupVO2.setSou_photo(getPicByteArray());
//	
//		dao.update(soupVO2);
		

		// 查詢
		SouvenirPhotoVO souphVO3 = dao.findByPrimaryKey("SOUPH007");
		System.out.print(souphVO3.getSou_photo_id() + ",");
		System.out.print(souphVO3.getSou_id() + ",");
		System.out.print(souphVO3.getSou_photo() + ",");
		System.out.println();
		System.out.println("---------------------");
//
//
//
		// 查詢
		List<SouvenirPhotoVO> list = dao.getAll();
		for (SouvenirPhotoVO souphVO4 : list) {

			System.out.print(souphVO4.getSou_photo_id() + ",");
			System.out.print(souphVO4.getSou_id() + ",");
			System.out.print(souphVO4.getSou_photo() + ",");
			System.out.println();
		}

	}
	
	
	public static byte[] getPicByteArray() throws IOException{
		File file = new File("WebContent/sou_img/ming.jpg");
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