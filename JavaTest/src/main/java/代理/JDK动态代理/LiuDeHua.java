package 代理.JDK动态代理;

//刘德华是一个明星
public class LiuDeHua implements Star {
    @Override
    public String sing(String name) {
        System.out.println(name);
        return "唱完";
    }

    @Override
    public String dance(String name) {
        System.out.println("开心的马骝");
        return "跳完";
    }
}
