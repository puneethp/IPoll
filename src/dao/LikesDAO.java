package dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class LikesDAO extends BaseHibernate{
	public Likes getLikes(String like_id)
	{
		Likes li = null;
		Session session=null;
		Criteria constraint=null;
		try
		{
			session = getSessionFactory().openSession();
			constraint=session.createCriteria(Likes.class);
			constraint.add(Restrictions.idEq(like_id));
			if(constraint.list().size()>0)
			{
				li = (Likes) constraint.list().get(0);
			}
		}catch(HibernateException e)
		{
			System.out.println("Error:Hibernate Exception\nFileName: LikesDAO Method:getLikes()");
			e.printStackTrace();
		}catch(ClassCastException c)
		{
			System.out.println("Error:Hibernate Configuration in Likes.hbm.xml");
		}finally
		{
			if(session!=null)
			session.close();
		}
		
	
		return li;
	}
	public boolean updateComment(Likes li)
	{
		Session session = null;
		Transaction trans = null;
		try
		{
			session = getSessionFactory().openSession();
			trans = session.beginTransaction();
			session.update(li);
			trans.commit();session.flush();
			return true;
		}catch(HibernateException e)
		{
			trans.rollback();
			System.out.println("Error: file:LikesDAO method:updateLike ");
			return false;
		}finally
		{
			if(session!=null)
				session.close();
		}
	}
	
	public boolean persistLikes(Likes li)
	{
		Session session = null;
		Transaction trans = null;
		try
		{
			session = getSessionFactory().openSession();
			trans = session.beginTransaction();
			session.save(li);
			trans.commit();session.flush();
			return true;
		}catch(HibernateException e)
		{
			trans.rollback();
			System.out.println("Error: file:LikesDAO method:persistLikes ");
			e.printStackTrace();
			return false;
		}finally
		{
			if(session!=null)
				session.close();
		}
	}
	
	public Long getLikesForBlog(String blog_id)
	{
		Session session=null;
		Criteria constraint=null;
		try
		{
			session = getSessionFactory().openSession();
			constraint=session.createCriteria(Likes.class);
			constraint.add(Restrictions.eq("blog_id", blog_id));
			return new Long(constraint.list().size());
		}catch(HibernateException e)
		{
			System.out.println("Error:Hibernate Exception\nFileName: LikesDAO Method:getLikesForBlog()");
			e.printStackTrace();
		}catch(ClassCastException c)
		{
			System.out.println("Error:Hibernate Configuration in Likes.hbm.xml");
		}finally
		{
			if(session!=null)
			session.close();
		}
		return 0L;
	}
	
	public boolean getLikesUserBlog(String blog_id,String user)
	{
		Session session=null;
		Criteria constraint=null;
		try
		{
			session = getSessionFactory().openSession();
			constraint=session.createCriteria(Likes.class);
			constraint.add(Restrictions.eq("blog_id", blog_id));
			constraint.add(Restrictions.eq("user_name", user));
			if(constraint.list().size()>0)
				return true;
		}catch(HibernateException e)
		{
			System.out.println("Error:Hibernate Exception\nFileName: LikesDAO Method:getLikesForBlog()");
			e.printStackTrace();
		}catch(ClassCastException c)
		{
			System.out.println("Error:Hibernate Configuration in Likes.hbm.xml");
		}finally
		{
			if(session!=null)
			session.close();
		}
		return false;
	}
}
