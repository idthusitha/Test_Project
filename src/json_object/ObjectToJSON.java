package json_object;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONObject;

public class ObjectToJSON {
	public static void main(String args[]) {

		TestBean bean = new TestBean();
		bean.setDoubleField(0.1);
		bean.setIntField(10);

		bean.setStringField("test value");

		Class objectClass = TestBean.class;

		JSONObject json = new ObjectToJSON().beanToJSON(bean);

		System.out.println(json.toString());

	}

	private JSONObject beanToJSON(Object bean) {
		JSONObject json = new JSONObject();

		try {
			Field[] fields = bean.getClass().getDeclaredFields();
			System.out.println(fields.length);
			
			for (Field f : fields) {
				String field = f.getName();
				Class params[] = {};
				Object paramsObj[] = {};

				Method method = bean.getClass().getDeclaredMethod("get" + StringUtils.capitalise(field), params);
				Object v = method.invoke(bean, paramsObj);

				Class t = f.getType();

				if (t == boolean.class && Boolean.FALSE.equals(v)) {
					json.accumulate(field, v.toString());

				} else if (t.isPrimitive() && ((Number) v).doubleValue() == 0) {
					json.accumulate(field, v.toString());

				} else if (t.isPrimitive() && ((Number) v).intValue() == 0) {
					json.accumulate(field, v.toString());

				} else if (t == String.class) {
					json.accumulate(field, v.toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
}
