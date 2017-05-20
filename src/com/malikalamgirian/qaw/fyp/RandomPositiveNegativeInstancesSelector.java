/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.malikalamgirian.qaw.fyp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author wasif
 */
public class RandomPositiveNegativeInstancesSelector {

    public RandomPositiveNegativeInstancesSelector(int number_Of_1_pairs, int number_Of_0_pairs,
            String output_Folder_Path_Name_URL, String suffix_To_Add_To_Filename, String input_TXT_File_Path_Name) throws Exception {
        /*
         * Set Internal State
         */
        this.input_TXT_File_Path_Name = input_TXT_File_Path_Name;
        this.number_Of_0_pairs = number_Of_0_pairs;
        this.number_Of_1_pairs = number_Of_1_pairs;
        this.suffix_To_Add_To_Filename = suffix_To_Add_To_Filename;
        this.output_Folder_Path_Name_URL = output_Folder_Path_Name_URL;

        /*
         * Set output Filename
         */
        this.setOutput_File_Path_Name(input_TXT_File_Path_Name, output_Folder_Path_Name_URL, suffix_To_Add_To_Filename);

        /*
         * Call Process
         */
        process();

    }

    /*
     * Declarations
     */
    private int number_Of_1_pairs = 0,
            number_Of_0_pairs = 0;
    private String output_Folder_Path_Name_URL = null,
            suffix_To_Add_To_Filename = null,
            input_TXT_File_Path_Name = null,
            output_File_Path_Name = null;

    private void process() throws Exception {
        /*
         * Local Declarations
         */
        List<String> cached_DataSet_0_Instances = null,
                cached_DataSet_1_Instances = null;

        String[] cached_DataSet_0_Instances_Array = null,
                cached_DataSet_1_Instances_Array = null;

        int random_Number = 0;

        List<Integer> negative_Examples_Selected,
                positive_Examples_Selected;

        BufferedReader bReader = null;
        BufferedWriter bWriter = null;

        String line_Read = null,
                line_To_Write = null;

        String attributes_Line = null;

        Boolean insertion_Made = false;

        try {
            /*
             * Set  Buffered Reader and  Buffered Writer
             */
            bReader = FileSystemManager.readFile(input_TXT_File_Path_Name);
            bWriter = FileSystemManager.createFile(this.output_File_Path_Name);

            /*
             * Initialize List
             */
            cached_DataSet_0_Instances = new ArrayList<String>();
            cached_DataSet_1_Instances = new ArrayList<String>();
            negative_Examples_Selected = new ArrayList<Integer>();
            positive_Examples_Selected = new ArrayList<Integer>();

            /*
             * Read and Cache 0 and 1 Instances
             */
            attributes_Line = bReader.readLine();

            while ((line_Read = bReader.readLine()) != null) {
                /*
                 * Test and Insert
                 */
                if (line_Read.substring(0, 1).equalsIgnoreCase("1")) {
                    /*
                     * Cache in '1'
                     */
                    cached_DataSet_1_Instances.add(line_Read);

                } else {
                    /*
                     * Cache in '0'
                     */
                    cached_DataSet_0_Instances.add(line_Read);
                }
            }

            /*
             * OUTPUT TEST
             */
            System.out.println("\nPositives Instances: " + cached_DataSet_1_Instances.size() + "\n");
            System.out.println("\nNegatives Instances: " + cached_DataSet_0_Instances.size() + "\n");
            System.out.println("\nTotal Instances : " + (cached_DataSet_1_Instances.size() + cached_DataSet_0_Instances.size()) + "\n");

            /*
             * Convert list into Arrays
             */
            cached_DataSet_0_Instances_Array = new String[cached_DataSet_0_Instances.size()];
            cached_DataSet_1_Instances_Array = new String[cached_DataSet_1_Instances.size()];

            cached_DataSet_0_Instances.toArray(cached_DataSet_0_Instances_Array);
            cached_DataSet_1_Instances.toArray(cached_DataSet_1_Instances_Array);

            /*
             * Write Attributes' Line
             */
            bWriter.write(attributes_Line);

            /*
             * NewLine
             */
            bWriter.newLine();

            /*
             * RAND Generation
             */
            Calendar calendar = GregorianCalendar.getInstance();
            calendar.setTime(new Date());

            /*
             * Write Random 1 pairs
             */
            if (number_Of_1_pairs == -1) {
                /*
                 * Write all 1 pairs
                 */
                for (int i = 0; i < cached_DataSet_1_Instances_Array.length; i++) {
                    /*
                     * Write String
                     */
                    bWriter.write(cached_DataSet_1_Instances_Array[i]);

                    /*
                     * NewLine
                     */
                    bWriter.newLine();

                    System.out.println(i + " / " + cached_DataSet_1_Instances_Array.length + " -- Positives. \n");
                }

            } else {
                for (int i = 0; i < number_Of_1_pairs; i++) {
                    /*
                     * Generate Random Number Unique
                     */
                    random_Number = new java.util.Random().nextInt(calendar.get(Calendar.SECOND) * calendar.get(Calendar.MILLISECOND) );

                    while (random_Number >= cached_DataSet_1_Instances_Array.length || positive_Examples_Selected.contains(random_Number)) {
                        /*
                         * Generate
                         */
                        random_Number = new java.util.Random().nextInt(calendar.get(Calendar.SECOND) * calendar.get(Calendar.MILLISECOND) );

                        System.out.println(random_Number);
                    }

                    /*
                     * Add the index value
                     */
                    positive_Examples_Selected.add(random_Number);

                    /*
                     * Write Randomly Selected Line
                     */
                    bWriter.write(cached_DataSet_1_Instances_Array[random_Number]);

                    /*
                     * NewLine
                     */
                    bWriter.newLine();

                    System.out.println(i + " / " + number_Of_0_pairs + " -- Positives. \n");
                }
            }


            /*
             * Write Random 0 pairs
             */
            if (number_Of_0_pairs == -1) {
                /*
                 * Write all 1 pairs
                 */
                for (int i = 0; i < cached_DataSet_0_Instances_Array.length; i++) {
                    /*
                     * Write String
                     */
                    bWriter.write(cached_DataSet_0_Instances_Array[i]);

                    /*
                     * NewLine
                     */
                    bWriter.newLine();

                    System.out.println(i + " / " + cached_DataSet_0_Instances_Array.length + " -- Negatives. \n");
                }

            } else {
                for (int i = 0; i < number_Of_0_pairs; i++) {
                    /*
                     * Generate Random Number Unique
                     */
                    random_Number = new java.util.Random().nextInt(calendar.get(Calendar.SECOND) * calendar.get(Calendar.MILLISECOND) );

                    while (random_Number >= cached_DataSet_0_Instances_Array.length || negative_Examples_Selected.contains(random_Number)) {
                        /*
                         * Generate
                         */
                        random_Number = new java.util.Random().nextInt(calendar.get(Calendar.SECOND) * calendar.get(Calendar.MILLISECOND));
                        
                        System.out.println(random_Number);
                    }

                    /*
                     * Add the index value
                     */
                    negative_Examples_Selected.add(random_Number);

                    /*
                     * Write Randomly Selected Line
                     */
                    bWriter.write(cached_DataSet_0_Instances_Array[random_Number]);

                    /*
                     * NewLine
                     */
                    bWriter.newLine();

                    System.out.println(i + " / " + number_Of_0_pairs + " -- Negatives. \n");
                }
            }

            /*
             * Flush and Close Reader Writer
             */
            bReader.close();
            bWriter.flush();
            bWriter.close();


        } catch (Exception ex) {
            throw new Exception("RandomPositiveNegativeInstancesSelector : process ");
        }
    }

    /*
     * Helper Methods
     */
    public String getOutput_File_Path_Name() {
        return output_File_Path_Name;
    }

    public void setOutput_File_Path_Name(String input_TXT_File_Path_Name, String output_Folder_Path_Name_URL,
            String suffix_To_Add_To_Filename) throws Exception {
        /*
         * Local Declarations
         */

        String extension = null;

        try {
            /*
             * Set Extension
             */
            extension = "txt";

            if (suffix_To_Add_To_Filename == null || suffix_To_Add_To_Filename.equals("") || suffix_To_Add_To_Filename.equals(" ")) {
                /*
                 * No Suffix Supplied
                 */
                this.output_File_Path_Name = output_Folder_Path_Name_URL + File.separatorChar + FileSystemManager.getFileNameWithExtension(input_TXT_File_Path_Name);
            } else {
                /*
                 * Suffix Has been Supplied
                 */
                this.output_File_Path_Name = output_Folder_Path_Name_URL + File.separatorChar + FileSystemManager.getFileNameWithExtension(FileSystemManager.addSuffixToFileURL(input_TXT_File_Path_Name, suffix_To_Add_To_Filename, extension));

            }

        } catch (Exception ex) {
            throw new Exception("RandomPositiveNegativeInstancesSelector : setOutput_File_Path_Name ");
        }
    }
}
