package SS3.bai6;

import java.util.List;
import java.util.stream.Collectors;

record Post(String title, List<String> tags) {}

public class Main {
    public static void main(String[] args) {
        List<Post> posts = List.of(
            new Post("Java Programming", List.of("java", "backend")),
            new Post("Python Data Science", List.of("python", "data")),
            new Post("Web Development", List.of("html", "css", "javascript"))
        );

        List<String> allTags = posts.stream()
            .flatMap(post -> post.tags().stream()) 
            .collect(Collectors.toList());         

        // 4. In kết quả mong muốn
        System.out.println("--- Danh sách tất cả các tag (đã làm phẳng) ---");
        System.out.println(allTags);
        
        // Hoặc in từng tag bằng forEach
        allTags.forEach(System.out::println);
    }
}