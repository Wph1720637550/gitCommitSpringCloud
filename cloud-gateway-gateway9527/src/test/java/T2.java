import java.time.ZonedDateTime;

public class T2 {

    public static void main(String[] args) {
        ZonedDateTime zbj = ZonedDateTime.now(); //默认时区,得到亚洲上海的当前时间

        //2020-11-01T15:04:57.651+08:00[Asia/Shanghai]  网关断言的After就是这个格式的时间
        System.out.println(zbj);
    }

}
