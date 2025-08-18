package util;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class GenerateCFGInfo {
    // PROGEX 可执行文件路径
    private static final String PROGEX_JAR_PATH = "D:\\progex-v3.4.5\\progex-v3.4.5\\PROGEX.JAR";

    public static void main(String[] args) {
        // mutants 文件夹路径
        String mutantsBaseDir = "D:\\bishe_code\\progex_benchmark\\mutant_programs\\BisectSqrt\\mutants";

        // 遍历 mutants 文件夹
        File mutantsDir = new File(mutantsBaseDir);
        if (!mutantsDir.exists() || !mutantsDir.isDirectory()) {
            System.err.println("Mutants 目录不存在或不是文件夹: " + mutantsBaseDir);
            return;
        }

        // 获取所有 mutant_XXX 文件夹
        File[] mutantDirs = mutantsDir.listFiles(file ->
                file.isDirectory() && file.getName().startsWith("mutant_")
        );

        if (mutantDirs == null || mutantDirs.length == 0) {
            System.err.println("未找到任何 mutant_XXX 文件夹");
            return;
        }

        // 为每个变异体生成 CFG 和 PDG
        Arrays.stream(mutantDirs).parallel().forEach(mutantDir -> {
            try {
                processMutant(mutantDir);
            } catch (IOException | InterruptedException e) {
                System.err.println("处理 " + mutantDir.getName() + " 时出错: " + e.getMessage());
            }
        });
    }

    /**
     * 生成图信息
     * @param mutantDir
     * @throws IOException
     * @throws InterruptedException
     */
    private static void processMutant(File mutantDir) throws IOException, InterruptedException {
        String mutantName = mutantDir.getName();
        System.out.println("正在处理: " + mutantName);

        // 在 mutant_XXX 下创建 outdir 文件夹
        Path outDir = Paths.get(mutantDir.getAbsolutePath(), "outdir");
        Files.createDirectories(outDir);

        // 查找变异体 Java 文件（假设每个 mutant_XXX 中只有一个 Java 文件）
        File[] javaFiles = mutantDir.listFiles((dir, name) -> name.endsWith(".java"));
        if (javaFiles == null || javaFiles.length == 0) {
            System.err.println(mutantName + " 中没有找到 Java 文件");
            return;
        }
        String javaFilePath = javaFiles[0].getAbsolutePath();

        String cfgCommand = String.format(
                "java -jar \"%s\" -cfg -lang java -format json -outdir \"%s\" \"%s\"",
                PROGEX_JAR_PATH, outDir.toString(), javaFilePath
        );
        executeCommand(cfgCommand);
    }

    /**
     * 执行命令
     * @param command
     * @throws IOException
     * @throws InterruptedException
     */
    private static void executeCommand(String command) throws IOException, InterruptedException {
        Process process = new ProcessBuilder()
                .command("cmd.exe", "/c", command)
                .inheritIO()
                .start();

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new IOException("命令执行失败: " + command);
        }
    }
}
