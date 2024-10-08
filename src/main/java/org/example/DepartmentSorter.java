package org.example;

import java.util.*;

public class DepartmentSorter {

    private static Set<String> addMissingDepartments(String[] departments) {
        Set<String> departmentSet = new HashSet<>();

        for (String dept : departments) {
            String[] parts = dept.split("\\\\");
            StringBuilder currentPath = new StringBuilder();

            for (String part : parts) {
                if (currentPath.length() > 0) {
                    currentPath.append("\\");
                }
                currentPath.append(part);
                departmentSet.add(currentPath.toString());
            }
        }

        return departmentSet;
    }

    private static List<String> sortDepartments(Set<String> departmentSet) {
        List<String> departmentList = new ArrayList<>(departmentSet);

        departmentList.sort((d1, d2) -> {
            String[] d1Parts = d1.split("\\\\");
            String[] d2Parts = d2.split("\\\\");
            int minLength = Math.min(d1Parts.length, d2Parts.length);

            for (int i = 0; i < minLength; i++) {
                int cmp = d2Parts[i].compareTo(d1Parts[i]);
                if (cmp != 0) {
                    return cmp;
                }
            }

            return Integer.compare(d1Parts.length, d2Parts.length);
        });

        return departmentList;
    }

    public static void main(String[] args) {
        String[] departments = {
                "K1\\SK1",
                "K1\\SK2",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K2",
                "K2\\SK1\\SSK1",
                "K2\\SK1\\SSK2"
        };

        Set<String> completeDepartments = addMissingDepartments(departments);
        List<String> sortedDepartments = sortDepartments(completeDepartments);

        for (String dept : sortedDepartments) {
            System.out.println(dept);
        }
    }
}
