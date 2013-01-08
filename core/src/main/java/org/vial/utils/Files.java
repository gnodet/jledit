/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.vial.utils;


import java.io.*;
import java.nio.charset.Charset;

public final class Files {

    static final int BUFFER_SIZE = 4096;

    private Files() {
        //Utility Class
    }

    /**
     * Reads a {@link File} and returns a {@String}.
     *
     * @param file
     * @param charset
     * @return
     * @throws IOException
     */
    public static String toString(File file, Charset charset) throws IOException {
        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;
        try {
            fis = new FileInputStream(file);
            bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[BUFFER_SIZE];
            int remaining;
            while ((remaining = fis.read(buffer)) > 0) {
                bos.write(buffer, 0, remaining);
            }
            if (charset != null) {
                return new String(bos.toByteArray(), charset);
            } else {
                return new String(bos.toByteArray());
            }

        } finally {
            Closeables.closeQuitely(fis);
            Closeables.closeQuitely(bos);
        }
    }

    /**
     * Reads a {@link File} and returns a {@String}.
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static String toString(File file) throws IOException {
        return toString(file, null);
    }

    public static void writeToFile(File file, String content, Charset charset) throws IOException {
        FileOutputStream fos = null;
        OutputStreamWriter writer = null;
        try {
            if (!file.exists() && !file.getParentFile().mkdirs()) {
                throw new FileNotFoundException("Could not find or create file:" + file.getName());
            }
            fos = new FileOutputStream(file);
            writer = new OutputStreamWriter(fos, charset);
            writer.write(content, 0, content.length());
            writer.flush();
        } finally {
            Closeables.closeQuitely(fos);
            Closeables.closeQuitely(writer);
        }
    }
}
