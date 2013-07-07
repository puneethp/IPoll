package dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class CommentsDAO extends BaseHibernate{
	public Comments getComments(String commentid)
	{
		Comments com = null;
		Session session=null;
		Criteria constraint=null;
		try
		{
			session = getSessionFactory().openSession();
			constraint=session.createCriteria(Comments.class);
			constraint.add(Restrictions.idEq(commentid));
			if(constraint.list().size()>0)
			{
				com = (Comments) constraint.list().get(0);
			}
		}catch(HibernateException e)
		{
			System.out.println("Error:Hibernate Exception\nFileName: CommentsDAO Method:getComment()");
			e.printStackTrace();
		}catch(ClassCastException c)
		{
			System.out.println("Error:Hibernate Configuration in Comments.hbm.xml");
		}finally
		{
			if(session!=null)
			session.close();
		}
		
	
		return com;
	}
	public boolean updateComment(Comments com)
	{
		Session session = null;
		Transaction trans = null;
		try
		{
			session = getSessionFactory().openSession();
			trans = session.beginTransaction();
			session.update(com);
			trans.commit();session.flush();
			return true;
		}catch(HibernateException e)
		{
			trans.rollback();
			System.out.println("Error: file:CommentsDAO method:updateComment ");
			return false;
		}finally
		{
			if(session!=null)
				session.close();
		}
	}
	
	public boolean persistComments(Comments com)
	{
		Session session = null;
		Transaction trans = null;
		try
		{
			session = getSessionFactory().openSession();
			trans = session.beginTransaction();
			session.save(com);
			trans.commit();session.flush();
			return true;
		}catch(HibernateException e)
		{
			trans.rollback();
			System.out.println("Error: file:CommentsDAO method:persistComment ");
			e.printStackTrace();
			return false;
		}finally
		{
			if(session!=null)
				session.close();
		}
	}
	
	public ArrayList<Comments> getCommentsForBlog(String blog_id)
	{
		ArrayList<Comments> comments = null;
		Session session = null;
		Criteria ctr = null;
		try
		{
			session = getSessionFactory().openSession();
			ctr = session.createCriteria(Comments.class);
			ctr.add(Restrictions.eq("blog_id", blog_id));
			comments = new ArrayList<Comments>();
			for(int i=0;i<ctr.list().size();i++)
				comments.add((Comments)ctr.list().get(i));
		}catch(HibernateException e)
		{
			System.out.println("Error:Hibernate Exception\nFileName: CommentsDAO Method:getCommentsForBlog()");
			e.printStackTrace();
		}catch(ClassCastException c)
		{
			System.out.println("Error:Hibernate Configuration in Comments.hbm.xml");
		}finally
		{
			if(session!=null)
			session.close();
		}
		return comments;
	}
	

}
