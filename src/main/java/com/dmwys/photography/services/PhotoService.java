package com.dmwys.photography.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.castor.Castors;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Entity;
import org.nutz.dao.entity.MappingField;
import org.nutz.dao.entity.PkType;
import org.nutz.dao.entity.Record;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.dao.util.cri.SqlExpression;
import org.nutz.dao.util.cri.SqlExpressionGroup;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.service.NameEntityService;

/**
 * 操作研究组数据库
 */
//@IocBean(name = "photoService", fields = "photo_dao"
public class PhotoService extends NameEntityService<Class<?>> {

    /**
     * 根据Id删除数据
     *
     * @param <T>
     * @param id  持久化Id
     * @return true 成功删除一条数据,false删除失败
     */
    public <T> boolean delById(int id, Class<T> c) {
        return this.dao().delete(c, id) == 1;
    }

    public <T> boolean delById(Long id, Class<T> c) {
        return this.dao().delete(c, id) == 1;
    }

    public <T> boolean delete(Object c) {
        return this.dao().delete(c) == 1;
    }

    public <T> T insertOrUpdate(T obj) {
        if (obj == null)
            return null;
        Entity<T> en = (Entity<T>) this.dao().getEntity(obj.getClass());
        if (en.getPkType() == PkType.UNKNOWN)
            throw new IllegalArgumentException("no support , without pks");
        boolean doInsert = false;
        switch (en.getPkType()) {
            case ID:
                Number n = (Number) en.getIdField().getValue(obj);
                if (n == null || n.intValue() == 0)
                    doInsert = true;
                break;
            case NAME:
                if (null == en.getNameField().getValue(obj))
                    doInsert = false;
                break;
            case COMPOSITE:
                doInsert = true;
                for (MappingField mf : en.getCompositePKFields()) {
                    Object v = mf.getValue(obj);
                    if (v != null) {
                        if (v instanceof Number && ((Number) v).intValue() != 0) {
                            continue;
                        }
                        doInsert = true;
                    }
                }
            case UNKNOWN:
                throw Lang.impossible();
        }
        if (doInsert) {
            return this.save(obj);
        } else {
            this.update(obj);
            return obj;
        }
    }

    /**
     * 根据ID查询一个对象
     *
     * @param <T>
     * @param id  持久化Id
     * @param c   要查询的表
     * @return 查询到的对象
     */
    public <T> T find(int id, Class<T> c) {
        return this.dao().fetch(c, id);
    }

    public <T> T find(Long id, Class<T> c) {
        return this.dao().fetch(c, id);
    }

    /**
     * 根据条件查询数据库中满足条件的数据
     *
     * @param <T>
     * @param c
     * @param condition
     * @return
     */
    public <T> List<T> search(Class<T> c, Condition condition) {
        return this.dao().query(c, condition, null);
    }

    public <T> List<Record> search(String table, Condition condition) {
        return this.dao().query(table, condition);
    }

    public <T> Record searchOne(String table, Condition condition) {
        List<Record> list = search(table, condition);
        return list == null ? null : list.get(0);
    }

    /**
     * 分页查询表中所有数据
     *
     * @param <T>
     * @param c           查询的表
     * @param currentPage 当前页数
     * @param pageSize    每页显示数量
     * @param orderby     desc排序的条件
     * @return List
     */
    public <T> List<T> searchByPage(Class<T> c, int currentPage, int pageSize, String orderby) {
        Pager pager = this.dao().createPager(currentPage, pageSize);

        return this.dao().query(c, Cnd.orderBy().desc(orderby), pager);
    }

    /**
     * 分页带条件查询所有数据
     *
     * @param <T>
     * @param c           查询的表
     * @param condition   查询条件,用Cnd的静态方法构造
     * @param currentPage 当前页码
     * @param pageSize    每页显示的数据量
     * @return List
     */
    public <T> List<T> searchByPage(Class<T> c, Condition condition, int currentPage, int pageSize) {
        Pager pager = this.dao().createPager(currentPage, pageSize);
        return this.dao().query(c, condition, pager);
    }
    
    /**
     * 分页带条件查询所有数据
     *
     * @param <T>
     * @param c           查询的表
     * @param condition   查询条件,用Cnd的静态方法构造
     * @param currentPage 当前页码
     * @param pageSize    每页显示的数据量
     * @return List
     */
    public <T> List<Record> searchRecordsByPage(String c, Condition condition, int currentPage, int pageSize) {
        Pager pager = this.dao().createPager(currentPage, pageSize);
        return this.dao().query(c, condition, pager);
    }

    /**
     * 修改一条数据
     *
     * @param <T>
     * @param t   修改数据库中的数据
     * @return true 修改成功,false 修改失败
     */
    public <T> boolean update(T t) {
        updateObj(t);
        return true;
    }

    public void updateObj(Object t) {
        this.dao().update(t);
    }

    /**
     * 根据条件修改指定数据
     *
     * @param <T>
     * @param c         数据库表
     * @param chain     修改的内容
     * @param condition 选择条件
     * @return true 成功,false失败
     */
    public <T> boolean update(Class<T> c, Chain chain, Condition condition) {
        return this.dao().update(c, chain, condition) > 0;
    }

    /**
     * 增加一条数据
     *
     * @param <T>
     * @param t
     * @return 返回增加到数据库的这条数据
     */
    public <T> T save(T t) {
        T t1 = this.dao().insert(t);
        return t1;
    }

    public void save(String table, Chain chain) {
        this.dao().insert(table, chain);
    }

    /**
     * 查询数据库中的数据条数
     *
     * @param <T>
     * @param c   查询的数据库表
     * @return int
     */
    public <T> int searchCount(Class<T> c) {
        return this.dao().count(c);
    }

    /**
     * 根据条件查询数据库中的数据条数
     *
     * @param <T>
     * @param c         查询的数据库表
     * @param condition 条件,用Cnd的静态方法构造
     * @return int
     */
    public <T> int searchCount(Class<T> c, Condition condition) {
        return this.dao().count(c, condition);
    }
    /**
     * 根据条件查询数据库中的数据条数
     *
     * @param <T>
     * @param c         查询的数据库表
     * @param condition 条件,用Cnd的静态方法构造
     * @return int
     */
    public <T> int searchRecordsCount(String c, Condition condition) {
        return this.dao().count(c, condition);
    }
    public <T> int searchCount(Class<T> c, Condition condition, HttpServletRequest request) {
        String tatal = request.getParameter("count");
        int count = 0;
        if (tatal == null) {
            count = searchCount(c, condition);// 总条目数
        } else {
            count = Integer.parseInt(tatal);
        }
        return count;
    }

    /**
     * 计算最大分页数
     *
     * @param count    记录总数
     * @param pageSize 每页显示多少数据
     * @return int
     */
    public int maxPageSize(int count, int pageSize) {
        if (pageSize > 0) {
            if ((count % pageSize) != 0) {
                return (count / pageSize) + 1;
            } else {
                return (count / pageSize);
            }
        }
        return 0;
    }

    /**
     * 根据多个id 查询数据
     *
     * @param <T>
     * @param ids 要查询的id,多个用","（逗号）分隔
     * @param c   要查询的表信息
     * @return List
     */
    public <T> List<T> searchByIds(Class<T> c, String ids, String orderby) {
        Entity<T> entity = this.dao().getEntity(c);

        String id = entity.getIdField().getColumnName();

        String sql = " " + id + " in (" + ids + ") order by " + orderby + " desc";

        return this.dao().query(c, Cnd.wrap(sql), null);

    }

    /**
     * 根据多个id 查询数据
     *
     * @param <T>
     * @param ids 整形的id数组
     * @param c   要查询的表信息
     * @return List
     */
    public <T> List<T> searchByIds(Class<T> c, int[] ids, String orderby) {
        Entity<T> entity = this.dao().getEntity(c);

        String id = entity.getIdField().getColumnName();

        return this.dao().query(c, Cnd.where(id, "in", ids).desc(orderby), null);

    }

    @Override
    public int deletex(Object... pks) {
        return super.deletex(pks);
    }

    @Override
    public int delete(String name) {
        return super.delete(name);
    }

    /**
     * 根据多个id删除数据
     *
     * @param <T>
     * @param c   要操作的表信息
     * @param ids 要删除的id,多个用","（逗号）分隔
     * @return true 成功,false 失败
     */
    public <T> void deleteByIds(Class<T> c, String ids) {
        Entity<T> entity = this.dao().getEntity(c);

        String table = entity.getTableName();

        String id = entity.getIdField().getColumnName();

        Sql sql = Sqls.create("delete from " + table + " where " + id + " in(" + ids + ")");

        this.dao().execute(sql);
    }

    /**
     * 根据条件返回一个条件
     *
     * @param <T>
     * @param condition 查询条件用Cnd构造
     * @return T
     */
    public <T> T findByCondition(Class<T> c, Condition condition) {
        return this.dao().fetch(c, condition);
    }

    /**
     * 模糊分页查询数据
     *
     * @param <T>
     * @param c           查询的数据库表
     * @param value       查询的字段
     * @param orderby     排序条件
     * @param currentPage 当前页面
     * @param pageSize    页面大小
     * @return List
     */
    public <T> List<T> searchPageByLike(Class<T> c, String value, String orderby, int currentPage,
                                        int pageSize) {
        Entity<T> entity = this.dao().getEntity(c);

        List<MappingField> fields = entity.getMappingFields();

        SqlExpressionGroup group = null;

        for (MappingField f : fields) {
            if (!f.isId()) {
                SqlExpression e = Cnd.exp(f.getColumnName(), "LIKE", "%" + value + "%");
                if (group == null) {
                    group = Cnd.exps(e);
                } else {
                    group.or(e);
                }
            }
        }


        Pager pager = this.dao().createPager(currentPage, pageSize);

        return this.dao().query(c, Cnd.where(group).desc(orderby), pager);
    }

    /**
     * 模糊分页查询数据记录总数
     *
     * @param <T>
     * @param c           查询的数据库表
     * @param value       查询的字段
     * @return List
     */
    public <T> int searchPageByLike(Class<T> c, String value) {
        Entity<T> entity = this.dao().getEntity(c);

        List<MappingField> fields = entity.getMappingFields();

        SqlExpressionGroup group = null;

        for (MappingField f : fields) {
            if (!f.isId()) {
                SqlExpression e = Cnd.exp(f.getColumnName(), "LIKE", "%" + value + "%");
                if (group == null) {
                    group = Cnd.exps(e);
                } else {
                    group.or(e);
                }
            }
        }


        return this.dao().count(c, Cnd.where(group));
    }

    /**
     * 根据指定的字段模糊分页查询数据
     *
     * @param <T>
     * @param c           查询的表
     * @param fieldName   字段名称
     * @param value       模糊条件
     * @param currentPage 当前页码
     * @param pageSize    每页数据量
     * @return List
     */
    public <T> List<T> searchByPageLike(Class<T> c, String fieldName, String value, int currentPage,
                                        int pageSize) {
        Entity<T> entity = this.dao().getEntity(c);

        String column = entity.getField(fieldName).getColumnName();

        Pager pager = this.dao().createPager(currentPage, pageSize);

        return this.dao().query(c, Cnd.where(column, "LIKE", "%" + value + "%"), pager);

    }

    /**
     * 根据指定的字段模糊分页查询数据 记录总数
     *
     * @param <T>
     * @param c           查询的表
     * @param fieldName   字段名称
     * @param value       模糊条件
     * @return List
     */
    public <T> int searchByPageLike(Class<T> c, String fieldName, String value) {
        Entity<T> entity = this.dao().getEntity(c);

        String column = entity.getField(fieldName).getColumnName();


        return this.dao().count(c, Cnd.where(column, "LIKE", "%" + value + "%"));

    }

    /**
     * 根据某个条件分页查询数据
     *
     * @param <T>
     * @param c           查询的表
     * @param fieldName   匹配字段名
     * @param value       匹配的值
     * @param currentPage 当前页码
     * @param pageSize    每页数据量
     * @return List
     */
    public <T> List<T> searchByPage(Class<T> c, String fieldName, String value, int currentPage,
                                    int pageSize) {
        Entity<T> entity = this.dao().getEntity(c);

        String column = entity.getField(fieldName).getColumnName();

        Pager pager = this.dao().createPager(currentPage, pageSize);

        return this.dao().query(c, Cnd.where(column, "=", value), pager);
    }

    /**
     * 根据指定条件返回一个对象
     *
     * @param <T>
     * @param fileName 匹配名称
     * @param value    匹配值
     * @return T
     */
    public <T> T findByCondition(Class<T> c, String fileName, String value) {
        return this.dao().fetch(c, Cnd.where(fileName, "=", value));
    }

    /**
     * 添加一条数据到数据库中， 该数据包括关联的多个其他数据
     *
     * @param <T>
     * @param t         插入数据库的对象
     * @param fieldName 关联数据的字段名，一般为List对象
     * @return T
     */
    public <T> T saveWidth(T t, String fieldName) {
        return this.dao().insertWith(t, fieldName);

    }

    /**
     * 获取关联对象
     *
     * @param <T>
     * @param t         查询的对象
     * @param fieldName 关联的对象
     * @return T
     */
    public <T> T findLink(T t, String fieldName) {
        return this.dao().fetchLinks(t, fieldName);
    }

    /**
     * 更新自身和关联的对象
     *
     * @param <T>
     * @param t         修改的对象
     * @param fieldName 关联对象
     * @return T
     */
    public <T> T updateWidth(T t, String fieldName) {
        return this.dao().updateWith(t, fieldName);
    }

    /**
     * 仅修改关联的对象的数据
     *
     * @param <T>
     * @param t         查询条件
     * @param fieldName 修改的对象
     * @return T
     */
    public <T> T updateLink(T t, String fieldName) {
        return this.dao().updateLinks(t, fieldName);
    }

    /**
     * 删除自身和关联对象
     *
     * @param <T>
     * @param t         删除的对象
     * @param fieldName 关联的对象
     */
    public <T> void deleteWidth(T t, String fieldName) {
        this.dao().deleteWith(t, fieldName);
    }

    /**
     * 删除关联的对象，不删除自身
     *
     * @param <T>
     * @param t         删除的条件
     * @param fieldName 删除的关联对象
     */
    public <T> void deleteLink(T t, String fieldName) {
        this.dao().deleteLinks(t, fieldName);
    }

    /**
     * 保存对象的多对多 关系
     *
     * @param <T>
     * @param t
     * @param fieldName
     */
    public <T> T saveRelation(T t, String fieldName) {
        return this.dao().insertRelation(t, fieldName);
    }

    /**
     * 保存对象的关联关系,不保存对象本身
     *
     * @param <T>
     * @param t
     * @param fieldName
     * @return
     */
    public <T> T saveLink(T t, String fieldName) {
        return this.dao().insertLinks(t, fieldName);
    }

    /**
     * 更新对象的多对多关系
     *
     * @param <T>
     * @param c         更新的对象的类
     * @param fieldName 更新的字段名称
     * @param chain     更新的内容
     * @param condition 更新的条件
     * @return true 成功,false 失败
     */
    public <T> boolean updateRelation(Class<T> c, String fieldName, Chain chain,
                                      Condition condition) {
        return this.dao().updateRelation(c, fieldName, chain, condition) > 0;
    }

    /**
     * 对于 '@One' 和 '@Many'，对应的记录将会删除 而 '@ManyMay' 对应的字段，只会清除关联表中的记录
     *
     * @param <T>
     * @param t
     * @param fieldName
     * @return
     */
    public <T> T clearRelation(T t, String fieldName) {
        return this.dao().clearLinks(t, fieldName);
    }

    /**
     * 根据中间表分页查询数据
     *
     * @param <T>
     * @param c           查询主表
     * @param joinTabel   中间表
     * @param cloumnName  要获取中间表的字段
     * @param condition   查询条件
     * @param group       主查询条件组
     * @param orderby     排序方式
     * @param currentPage 当前页面
     * @param pageSize    每页显示数据
     * @return
     */
    public <T> List<T> searchByRelation(Class<T> c, String joinTabel, String cloumnName,
                                        Condition condition, SqlExpressionGroup group, String orderby, int currentPage,
                                        int pageSize) {
        Entity<T> entity = this.dao().getEntity(c);

        List<Record> records = this.dao().query(joinTabel, condition, null);

        List<Integer> ids = new ArrayList<Integer>();
        for (Record r : records) {
            int id = r.getInt(cloumnName);
            ids.add(id);
        }
        if (ids.size() == 0) {
            return null;
        }

        Pager pager = this.dao().createPager(currentPage, pageSize);

        SqlExpression e =
                Cnd.exp(entity.getIdField().getColumnName(), "in", Castors.me().castTo(ids, int[].class));

        ids = null;

        return this.dao().query(c, Cnd.where(group.and(e)).desc(orderby), pager);
    }

    /**
     * 查询数据的总数
     *
     * @param <T>
     * @param c
     * @param joinTabel  中间表
     * @param cloumnName 要获取中间表的字段
     * @param condition  查询条件
     * @param group      主查询条件组
     * @param orderby    排序方式
     * @return
     */
    public <T> int searchCount(Class<T> c, String joinTabel, String cloumnName, Condition condition,
                               SqlExpressionGroup group, String orderby) {
        Entity<T> entity = this.dao().getEntity(c);

        List<Record> records = this.dao().query(joinTabel, condition, null);

        List<Integer> ids = new ArrayList<Integer>();
        for (Record r : records) {
            int id = r.getInt(cloumnName);
            ids.add(id);
        }
        if (ids.size() == 0) {
            return 0;
        }

        SqlExpression e =
                Cnd.exp(entity.getIdField().getColumnName(), "in", Castors.me().castTo(ids, int[].class));

        group = group.and(e);

        return this.dao().count(c, Cnd.where(group).desc(orderby));
    }

    public void delete(String table, Condition condition) {
        this.dao().clear(table, condition);
    }

    /**
     * 查询用户投注的统计信息
     *
     * @return
     */
    public Map<Integer, Integer> getUserBetStatic() {
        Sql sql = Sqls.create(
                "select user_id,count(1) as sum from kuyun_cards_bowls_user_bets where deleted = 0 group by user_id");
        sql.setCallback(new SqlCallback() {
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                Map<Integer, Integer> map = new HashMap<Integer, Integer>();
                while (rs.next()) {
                    map.put(rs.getInt("user_id"), rs.getInt("sum"));
                }
                return map;
            }
        });
        this.dao().execute(sql);
        return (Map<Integer, Integer>) sql.getResult();
    }

    @SuppressWarnings("unchecked")
    public List<Record> queryBysql(String sqlStr) {
        Sql sql = Sqls.create(sqlStr);
        this.dao().execute(sql.setCallback(new SqlCallback() {
            @Override
            public List<Record> invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<Record> result = new ArrayList<Record>();
                while (rs.next()) {
                    result.add(Record.create(rs));
                }
                return result;
            }
        }));
        return (List<Record>) sql.getResult();
    }

    public List<Record> queryBysql(Sql sql) {
        this.dao().execute(sql.setCallback(new SqlCallback() {
            @Override
            public List<Record> invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<Record> result = new ArrayList<Record>();
                while (rs.next()) {
                    result.add(Record.create(rs));
                }
                return result;
            }
        }));
        return (List<Record>) sql.getResult();
    }

    @SuppressWarnings("unchecked")
    public List<Record> querySqlByPage(String sqlStr, int currentPage, int pageSize) {
        Sql sql = Sqls.create(sqlStr);
        sql.setPager(this.dao().createPager(currentPage, pageSize));
        this.dao().execute(sql.setCallback(new SqlCallback() {
            @Override
            public List<Record> invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<Record> result = new ArrayList<Record>();
                while (rs.next()) {
                    result.add(Record.create(rs));
                }
                return result;
            }
        }));
        return (List<Record>) sql.getResult();
    }

    public Record queryOneBysql(String sqlStr) {
        List<Record> select = this.queryBysql(sqlStr);
        if (select != null && select.size() > 0) {
            return select.get(0);
        } else {
            return null;
        }
    }

    public void excuteSQL(String sqlStr) {
        Sql sql = Sqls.create(sqlStr);
        this.dao().execute(sql);
    }

    public static void main(String[] args) {
        Date d = new Date(1425373870073L);
        System.out.println(d);
    }

    public <T> boolean deleteByIds(Class<T> clz, List<Integer> id_list) {
        if (id_list != null && id_list.size() > 0) {
            String ids = Lang.concat(",", id_list).toString();
            deleteByIds(clz, ids);
            return true;
        }
        return true;
    }

    public <T> T searchOne(Condition condition, Class<T> clz) {
        List<T> list = search(clz, condition);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    public void execute(Sql sql) {
        this.dao().execute(sql);
    }

    public int querySqlCount(String sql) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(1) FROM ( ");
        sb.append(sql);
        sb.append(") t ");
        Sql s = Sqls.fetchLong(sb.toString());
        dao().execute(s);
        return s.getInt();
    }
}
