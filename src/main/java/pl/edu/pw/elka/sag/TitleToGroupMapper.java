package pl.edu.pw.elka.sag;

import java.io.*;
import java.util.*;

public class TitleToGroupMapper {
    private final BufferedReader bufferedStream;
    private final List<List<String>> groups = new ArrayList<List<String>>();

    public TitleToGroupMapper(InputStream inputStream) {
        bufferedStream = new BufferedReader(new InputStreamReader(inputStream));
    }

    public void readGroups() {
        String line;
        try {
            while ((line = bufferedStream.readLine()) != null) {
                final String[] titles = line.split("\t");
                groups.add(Arrays.asList(titles));
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public Map<String, Integer> getTitleToGroupMapping() {
        final HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < groups.size(); ++i) {
            final List<String> group = groups.get(i);
            for (final String title : group) {
                map.put(title, i);
            }
        }

        return map;
    }

    public static void main(String[] args) {
        FileInputStream fileStream = null;
        try {
            fileStream = new FileInputStream("input.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        final TitleToGroupMapper mapper = new TitleToGroupMapper(fileStream);
        mapper.readGroups();

        final Map<String, Integer> titleToGroupMap = mapper.getTitleToGroupMapping();

        System.out.println("Number of keys: " + titleToGroupMap.size());
        System.out.println("Group of United_States is " + titleToGroupMap.get("United_States"));
        System.out.println("Group of France is " + titleToGroupMap.get("France"));
    }
}
