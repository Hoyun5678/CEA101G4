package com.news.model;

import java.sql.Clob;
import java.sql.Timestamp;
import java.util.List;

public class NewsService {
	private NewsDAO_interface dao;
	public NewsService() {
		dao = new NewsDAO();
	}

	public NewsVO addNews(String news_content, Timestamp news_date) {

		NewsVO newsVO = new NewsVO();
		newsVO.setNews_content(news_content);
		newsVO.setNews_date(news_date);
		dao.insert(newsVO);

		return newsVO;
	}

	public NewsVO updateNews(String news_content,Timestamp news_date, String news_id) {

		NewsVO newsVO = new NewsVO();

		newsVO.setNews_content(news_content);
		newsVO.setNews_date(news_date);
		newsVO.setNews_id(news_id);
		dao.update(newsVO);

		return newsVO;
	}

	public void deleteNews(String news_id) {
		dao.delete(news_id);
	}

	public NewsVO getOneNews(String news_id) {
		return dao.findByPrimaryKey(news_id);
	}

	public List<NewsVO> getAll() {
		return dao.getAll();
	}
}
