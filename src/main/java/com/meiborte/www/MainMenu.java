package com.meiborte.www;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainMenu {

    //创建一个集合来存储，数组
    static List<Bills> billList=new ArrayList<>();

    //类加载的时候会第一时间执行，在billsList里面加入初始化的数据
    static {
        billList.add(new  Bills("吃饭","现金","支出",234.0,"2023-09-18","聚会"));
        billList.add(new  Bills("工资","交行","收入",4330.0,"2023-10-18","开工资"));
        billList.add(new  Bills("衣服","现金","支出",431.0,"2023-09-30","约会"));
        billList.add(new  Bills("吃饭","现金","支出",24.0,"2023-08-26","聚会"));
        billList.add(new  Bills("股票","现金","支出",432.0,"2023-01-21","聚会"));
        billList.add(new  Bills("其他","工商","支出",3242.0,"2023-04-12","聚会"));
        billList.add(new  Bills("交通","工行","支出",453.0,"2023-07-23","聚会"));
        billList.add(new  Bills("吃饭","建设","支出",454.0,"2023-02-23","聚会"));
        billList.add(new  Bills("喝水","工商","收入",6546.0,"2023-04-21","聚会"));
        billList.add(new  Bills("服装","建设","收入",234.0,"2023-08-31","聚会"));
    }

    public static void main(String[] args) {
        run();
    }

    public static void showMain(){
        System.out.println("--------随手记--------");
        System.out.println("1.添加账务 2，删除账务 3.查询账务 4.退出账务");
        System.out.println("请输入功能序号【1-4】：");
    }

    public static void run(){
        showMain();

        //创建一个flag标志
        boolean flag=true;
        //创建扫描器
        Scanner scanner=new Scanner(System.in);
        while (flag) {
            //获取输入的选项
            int op = scanner.nextInt();
            //判断1,2,3,4
            switch (op) {
                case 1:
                    addBills();
                    break;
                case 2:
                    delBills();
                    break;
                case 3:
                    select();
                    break;
                case 4:
                    flag=false;
                    break;
                default:
                    System.out.println("请重新输入：");
            }
        }
        System.out.println("推出系统,拜拜");
    }

    private static void delBills() {
        System.out.println("随手记>>账务删除");
        Scanner inScanner=new Scanner(System.in);
        int id=inScanner.nextInt();
        billList.remove(id-1);
        System.out.println("删除成功");
        showMain();
    }

    private static void addBills() {
        System.out.println("随手记>>账务添加");
        Scanner inScanner=new Scanner(System.in);
        Bills bills=new Bills();

        System.out.println("请输入类别：");
        bills.setName(inScanner.next());
        System.out.println("请输入账户：");
        bills.setAccount(inScanner.next());
        System.out.println("请输入支出收入类型：");
        bills.setType(inScanner.next());
        System.out.println("请输入金额：");
        bills.setTotal(inScanner.nextDouble());
        System.out.println("请输入时间：");
        bills.setTime(inScanner.next());
        System.out.println("请输入备注：");
        bills.setDesc(inScanner.next());

        billList.add(bills);
        System.out.println("添加服务成功：");
        showMain();
    }

    /**
     * 三种查询 1.查询所有， 2，按照时间区间来查询，3.收入和支出的类型查询
     */
    public static void select(){
        System.out.println("随手记>>账务查询");
        System.out.println("三种查询 1.查询所有， 2，按照时间区间来查询，3.收入和支出的类型查询");
        //创建扫描器
        Scanner scanner=new Scanner(System.in);
        int op=scanner.nextInt();
        switch (op){
            case 1:
                selectAll();
                break;
            case 2:
                selectByDate();
                break;
            case 3:
                selectByType();
                break;
        }
        //展示上一级的主菜单
        showMain();
    }

    private static void selectByType() {
        System.out.println("随手记>>账务查询>>收入和支出的类型");
        System.out.println("请输入：收入或者支出");
        //创建扫描器
        Scanner scanner=new Scanner(System.in);
        //收入，支出
        String type=scanner.next();
        //筛选一下集合中type是支出的元素，集合中的查询，1.8之前集合是有缺陷，不能查询，1.8之后，我们可以根据自己的想法做筛选
        //集合stream流
        List<Bills>billsList1=billList.stream()
                .filter(bills -> {
                    String type1=bills.getType();
                    return type1.equals(type);
                }).collect(Collectors.toList());
        print(billsList1);

    }

    private static void selectByDate() {
        //创建一个时间格式化的对象
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");

        System.out.println("随手记>>账务查询>>按照时间区间来查询");
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入：开始时间");
        String start =scanner.next();
        System.out.println("请输入：结束时间");

        String end =scanner.next();
        List<Bills>billsList=billList.stream().filter(bills -> {
            String time= bills.getTime();
            //把字符串解析成具体的时间
            try {
                Date startDate=format.parse(start);
                Date endDate=format.parse(end);
                Date timeDate=format.parse(time);
                if(timeDate.before(endDate)&&timeDate.after(startDate)){
                    return true;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return false;
        }).collect(Collectors.toList());
        print(billsList);
    }

    private static void selectAll() {
        print(billList);
        
    }
    public static void print(List<Bills>billsList){
        System.out.println("ID\t\t类别\t\t\t账户\t\t类型\t\t\t金额\t\t\t时间\t\t\t\t备注");
        for (int i = 0; i < billsList.size(); i++) {
            Bills bills =billsList.get(i);
            System.out.println(i+1+"\t\t"+bills.getName()+"\t\t\t"+bills.getAccount()+"\t\t"+bills.getType()+"\t\t\t"+bills.getTotal()+"\t\t"+bills.getTime()+"\t\t\t\t"+bills.getDesc());
        }
    }
}
