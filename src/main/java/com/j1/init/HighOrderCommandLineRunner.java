package com.j1.init;

/**
 * Created by wangchuanfu on 20/7/30.
 *
 * @Slf4j
 * @Component public class HighOrderCommandLineRunner<T> implements CommandLineRunner,Ordered {
 * @Override public void run(String... strings) throws Exception {
 * <p>
 * <p>
 * log.info("i am highOrderRunner");
 * System.out.println("MyApplicationRunner==========haha======" );
 * <p>
 * try {
 * // 获得超类，e.g.：Service2 extends
 * // Service1<Clazz>，Service2中执行，获得Service1的Type
 * Class<?> tclazz = getClass();
 * // $$EnhancerByCGLIB 代理对象无泛型，不执行取泛型操作
 * if (tclazz.getName().indexOf("$$EnhancerByCGLIB") == -1) {
 * Type type = getClass().getGenericSuperclass();
 * <p>
 * // 从此类型实际类型参数的 Type对象的数组中获取泛型对象的实例
 * Type trueType = ((ParameterizedType) type).getActualTypeArguments()[0];
 * // 对象转型
 * this.clazz = (Class<T>) trueType;
 * <p>
 * // 获得T里面的所有字段
 * fields = clazz.getDeclaredFields();
 * Field.setAccessible(fields, true);
 * <p>
 * <p>
 * <p>
 * // 获得es索引注解，得到indexName和indexType
 * EsIndex esIndex = clazz.getAnnotation(EsIndex.class);
 * if (esIndex == null) {
 * throw new NotEsIndexException();
 * }
 * // 获得T里面设置的索引名
 * setIndexName(esIndex.indexName());
 * // 获得T里面设置的索引类型
 * setIndexTypes(esIndex.indexTypes());
 * <p>
 * }
 * } catch (Exception e) {
 * e.printStackTrace();
 * }
 * <p>
 * }
 * @Override public int getOrder() {
 * <p>
 * return Integer.MIN_VALUE+1;
 * }
 * }
 */
