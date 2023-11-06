package hackerank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *  you are given a log file with a list of GET requests delimited with double quotes and spaces
 *  Sample log record: Hostname - - [Timestamp] "Requesst String" HTTP respose code Bytes
 *  Given a filename taht denotes a text file in current working directory. Create an output file with the name "bytes_" prefixed to the filename (bytes_filename) which stores the informantion about large respoeses.
 *  Example: filename = "hosts_access_log_00.txt", process the records in hosts_access_log_00.txt and create an output file named bytes_hosts_access_log_00.txt.
 *  Write the following to the output file:
 *  1. The first line must contain the number of requests that have more than 5000 bytes sent in their response.
 *  2. the second line must contain the total sum of bytes sent by all responses sending more than 5000 bytes.
 */
public class LogProcessor {
    public static void main(String[] args) {
        String inputFilename = "hosts_access_log_00.txt"; // Replace with your input file name
        String outputFilename = "bytes_" + inputFilename;

        int requestsCount = 0;
        long totalBytes = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilename)) ;
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilename))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length >= 9) {
                    try {
                        long bytes = Long.parseLong(parts[parts.length - 1]);
                        if (bytes > 5000) {
                            requestsCount++;
                            totalBytes += bytes;
                        }
                    } catch (NumberFormatException e) {
                        // Ignore lines with invalid byte values.
                    }
                }
            }

            // Write results to the output file
            writer.write(Integer.toString(requestsCount));
            writer.newLine();
            writer.write(Long.toString(totalBytes));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Processed log file. Results written to " + outputFilename);
    }
}

