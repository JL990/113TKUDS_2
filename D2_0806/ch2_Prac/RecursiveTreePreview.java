package D2_0806.ch2_Prac;
import java.util.*;

public class RecursiveTreePreview {

    // 1. 模擬資料夾與檔案系統結構
    static class FileNode {
        String name;
        boolean isFile;
        List<FileNode> children;

        FileNode(String name, boolean isFile) {
            this.name = name;
            this.isFile = isFile;
            if (!isFile) {
                children = new ArrayList<>();
            }
        }

        void addChild(FileNode child) {
            if (!isFile) {
                children.add(child);
            }
        }
    }

    // 遞迴計算資料夾中所有檔案數
    public static int countFiles(FileNode node) {
        if (node.isFile) return 1;

        int count = 0;
        for (FileNode child : node.children) {
            count += countFiles(child);
        }
        return count;
    }

    // 2. 遞迴列印多層選單結構
    static class MenuItem {
        String name;
        List<MenuItem> subMenus;

        MenuItem(String name) {
            this.name = name;
            subMenus = new ArrayList<>();
        }

        void addSubMenu(MenuItem menu) {
            subMenus.add(menu);
        }
    }

    public static void printMenu(MenuItem menu, int level) {
        for (int i = 0; i < level; i++) System.out.print("  ");
        System.out.println("- " + menu.name);
        for (MenuItem sub : menu.subMenus) {
            printMenu(sub, level + 1);
        }
    }

    // 3. 遞迴展平巢狀陣列 (Object[] 可包含 Integer 或 Object[] 本身)
    public static List<Integer> flattenNestedArray(Object[] arr) {
        List<Integer> result = new ArrayList<>();
        flattenHelper(arr, result);
        return result;
    }

    private static void flattenHelper(Object[] arr, List<Integer> result) {
        for (Object obj : arr) {
            if (obj instanceof Integer) {
                result.add((Integer) obj);
            } else if (obj instanceof Object[]) {
                flattenHelper((Object[]) obj, result);
            }
        }
    }

    // 4. 遞迴計算巢狀清單最大深度
    public static int maxDepth(List<?> nestedList) {
        int max = 1;
        for (Object obj : nestedList) {
            if (obj instanceof List) {
                max = Math.max(max, 1 + maxDepth((List<?>) obj));
            }
        }
        return max;
    }

    // 測試主程式
    public static void main(String[] args) {
        // 測試 1. 計算檔案數
        FileNode root = new FileNode("root", false);
        root.addChild(new FileNode("file1.txt", true));
        FileNode folderA = new FileNode("folderA", false);
        folderA.addChild(new FileNode("file2.txt", true));
        folderA.addChild(new FileNode("file3.txt", true));
        root.addChild(folderA);
        System.out.println("總檔案數: " + countFiles(root));  // 預期 3

        // 測試 2. 列印多層選單
        MenuItem menu = new MenuItem("主選單");
        MenuItem sub1 = new MenuItem("設定");
        MenuItem sub2 = new MenuItem("關於");
        MenuItem sub11 = new MenuItem("語言");
        sub1.addSubMenu(sub11);
        menu.addSubMenu(sub1);
        menu.addSubMenu(sub2);
        System.out.println("\n選單結構：");
        printMenu(menu, 0);

        // 測試 3. 展平巢狀陣列
        Object[] nestedArr = {1, new Object[]{2, 3, new Object[]{4}}, 5};
        List<Integer> flat = flattenNestedArray(nestedArr);
        System.out.println("\n展平陣列結果: " + flat);

        // 測試 4. 巢狀清單最大深度
        List<Object> nestedList = Arrays.asList(
            1,
            Arrays.asList(2, 3),
            Arrays.asList(Arrays.asList(4, 5), 6),
            7
        );
        System.out.println("\n巢狀清單最大深度: " + maxDepth(nestedList));  // 預期 3
    }
}
