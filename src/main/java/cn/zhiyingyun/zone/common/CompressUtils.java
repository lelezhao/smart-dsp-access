package cn.zhiyingyun.zone.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.*;

public class CompressUtils {

  private static final Logger log = LoggerFactory.getLogger(CompressUtils.class);
  private static final int BUFFER_SIZE = 1024;

  /**
   * Gzip编码
   */
  public static byte[] gzipEncode(byte[] data) {

    if (data == null) {
      return null;
    }

    byte[] encodeBytes = null;

    try (ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
         GZIPOutputStream gzipOut = new GZIPOutputStream(byteOut)) {

      gzipOut.write(data);
      gzipOut.finish();
      encodeBytes = byteOut.toByteArray();

    } catch (Exception e) {
      log.error("[EXCEPTION]CompressUtils.gzipEncode", e.getMessage());
    }
    return encodeBytes;
  }

  /**
   * Gzip解码
   */
  public static byte[] gzipDecode(byte[] data) {

    if (data == null) {

      log.error("[ERROR]CompressUtils.gzipDecode", "The parameter data is null!");
      return null;
    }
    byte[] decodeBytes;

    try (ByteArrayInputStream byteIn = new ByteArrayInputStream(data);
         GZIPInputStream gzipIn = new GZIPInputStream(byteIn);
         ByteArrayOutputStream byteOut = new ByteArrayOutputStream()) {

      byte[] buffer = new byte[BUFFER_SIZE];
      int count;
      while ((count = gzipIn.read(buffer, 0, buffer.length)) != -1) {
        byteOut.write(buffer, 0, count);
      }
      decodeBytes = byteOut.toByteArray();
      byteOut.flush();
    } catch (Exception e) {
      log.error("[Exception]Compress.gzipDecode", e.getMessage());
      decodeBytes = null;
    }
    return decodeBytes;
  }

  /**
   * Deflate编码
   */
  public static byte[] deflateEncode(byte[] data) {
    if (data == null) {

      log.error("[ERROR]CompressUtils.deflateEncode", "The parameter data is null!");
      return null;
    }
    byte[] encodeBytes = null;

    try (ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
         DeflaterOutputStream deflateOut = new DeflaterOutputStream(byteOut, new Deflater(Deflater.DEFAULT_COMPRESSION, true))) {

      deflateOut.write(data);
      deflateOut.finish();
      deflateOut.close();
      encodeBytes = byteOut.toByteArray();

    } catch (Exception e) {
      log.error("[Exception]CompressUtils.deflateEncode", e.getMessage());
    }
    return encodeBytes;
  }

  /**
   * Deflate解码
   */
  public static byte[] deflateDecode(byte[] data) {

    if (data == null) {
      log.error("[ERROR]CompressUtils.deflateDecode", "The parameter data is null!");
      return null;
    }

    byte[] decodeBytes;

    try (ByteArrayInputStream byteIn = new ByteArrayInputStream(data);
         DeflaterInputStream defalteIn = new DeflaterInputStream(byteIn);
         ByteArrayOutputStream byteOut = new ByteArrayOutputStream()) {

      byte[] buffer = new byte[BUFFER_SIZE];
      int count;
      while ((count = defalteIn.read(buffer, 0, buffer.length)) != -1) {
        byteOut.write(buffer, 0, count);
      }
      decodeBytes = byteOut.toByteArray();
      byteOut.flush();
    } catch (Exception e) {
      log.error("[Exception]CompressUtils.deflateDecode", e.getMessage());
      decodeBytes = null;
    }
    return decodeBytes;
  }
}
