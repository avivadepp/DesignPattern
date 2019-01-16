package StreamALambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class StreamMain {
    public static void main(String[] args){
        //过滤空串
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        //List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        //strings.stream().filter(string -> !string.isEmpty()).forEach(System.out::println);
        //并行程序,不是顺序输出
        strings.parallelStream().filter(string -> !string.isEmpty()).forEach(System.out::println);
        /*for(String i:filtered){
            System.out.println(i);
        }*/

        //forEach用来迭代流中的每一个数据，limit用于获取指定数量的流
        /*Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);*/

        //map用于映射每个元素到对应的结果，distinct用来剔除重复元素
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        numbers.stream().map( i -> i*i).distinct().sorted().forEach(System.out::println);
        //stream操作不改变原数组结果
        numbers.stream().forEach(System.out::println);
        //numbers.stream().map( i -> i*i).distinct().sorted().forEach(System.out::println);

        //Collectors 类实现了很多归约操作，例如将流转换成集合和聚合元素。Collectors 可用于返回列表或字符串：
        System.out.println(strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(",")));

        //flatMap的作用是将流中的每一个元素都转换成一个个流，再将这些流扁平化为一个流。
        List<String> strs = Arrays.asList("好,好,学", "习,天,天", "向,上");
        List<String> strList1 = strs.stream().map(str -> str.split(",")).flatMap(Arrays::stream)
                .collect(Collectors.toList());
        System.out.println(strList1);

        //用flatmap实现排列
        List<String> arr1 = Arrays.asList("1","2","3");
        List<String> arr2 = Arrays.asList("a","b","c");
        List<String> arr3 = Arrays.asList("A","B","C");
        List<String[]> sets=arr1.parallelStream().flatMap
                (a->arr2.parallelStream().flatMap(b->arr3.parallelStream().map(c->new String[]{a,b,c})))
                .collect(Collectors.toList());
        for(int i=0;i<sets.size();i++){
            System.out.println(Arrays.toString(sets.get(i)));
        }
    }
}
