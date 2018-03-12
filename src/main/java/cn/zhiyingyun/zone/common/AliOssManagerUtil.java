package cn.zhiyingyun.zone.common;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.util.List;

/**
 * Created by Joy.Zhao on 2017/4/12.
 */
public class AliOssManagerUtil {

  private static final String accessKeyId = "LTAISEzznWhRyyIB";

  private static final String accessKeySecret = "phiXu1K3v8kiibWGcfcJuk6m8tH3XL";

  private static final String endpoint = "http://oss-cn-beijing.aliyuncs.com";

  private static final String imgEndpoint = "oss-cn-beijing.aliyuncs.com";

  private static final String bucketName = "smartad-asset";

  private static final String cdn = "http://cdn.apilnk.com/smartad/";

  /**
   * 通过文件名，获取文件在OSS上的HTTP访问地址
   *
   * @param fileName 文件名称
   * @return
   */
  public static String getOrignPictureUrl(String fileName) {
    String url = null;
    if (StringUtils.isNotBlank(fileName)) {
      url = cdn + fileName;
    }
    return url;
  }

  public static String getRelativePath(String fileName) {
    String url = null;
    if (StringUtils.isNotBlank(fileName)) {
      url = "/smartad/" + fileName;
    }
    return url;
  }

  /**
   * 获取经过缩放的图片http地址
   *
   * @param fileName 文件名称
   * @return
   */
  public static String getLittlePictureUrl(String fileName) {
    String url = null;
    if (StringUtils.isNotBlank(fileName)) {
      url = "http://" + bucketName + "." + imgEndpoint + "/" + fileName + "@40w";
    }
    return url;
  }

  /**
   * 获取经过缩放的图片http地址
   *
   * @param fileName 文件名称
   * @return
   */
  public static String getMidPictureUrl(String fileName) {
    String url = null;
    if (org.apache.commons.lang.StringUtils.isNotBlank(fileName)) {
      url = "http://" + bucketName + "." + imgEndpoint + "/" + fileName + "@150w";
    }
    return url;
  }

  /**
   * 上传文件到OSS中
   *
   * @param multipartFile
   * @param uploadName
   * @return 是否上传成功
   * @throws IOException
   */
  public static Boolean uploadFile(MultipartFile multipartFile, String uploadName, String contentType, String contentDisposition) throws IOException {
    OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    // 获取Bucket的存在信息
    boolean exists = client.doesBucketExist(bucketName);
    if (!exists) {
      // 新建一个Bucket
      client.createBucket(bucketName);
      //CannedAccessControlList是枚举类型，包含三个值： Private 、 PublicRead 、 PublicReadWrite
      client.setBucketAcl(bucketName, CannedAccessControlList.PublicReadWrite);
    }

    // 获取指定文件的输入流
    InputStream content = multipartFile.getInputStream();

    // 创建上传Object的Metadata
    ObjectMetadata meta = new ObjectMetadata();

    // 必须设置ContentLength
    meta.setContentLength(multipartFile.getSize());

    if (contentType != null) {
      meta.setContentType(contentType);
    }
    if (contentDisposition != null) {
      meta.setContentDisposition(contentDisposition);
    }

    // 上传Object.
    PutObjectResult result = client.putObject(bucketName, "smartad/" + uploadName, content, meta);

    // 打印ETag
    System.out.println(result.getETag());

    return true;
  }

  /**
   * 上传文件到OSS中
   *
   * @param bytes
   * @param uploadName
   * @return 是否上传成功
   * @throws IOException
   */
  public static Boolean uploadFileByBytes(byte[] bytes, String uploadName) throws IOException {
    OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    // 获取Bucket的存在信息
    boolean exists = client.doesBucketExist(bucketName);
    if (!exists) {
      // 新建一个Bucket
      client.createBucket(bucketName);
      //CannedAccessControlList是枚举类型，包含三个值： Private 、 PublicRead 、 PublicReadWrite
      client.setBucketAcl(bucketName, CannedAccessControlList.PublicReadWrite);
    }

    // 获取指定文件的输入流
    InputStream content = new ByteArrayInputStream(bytes);

    // 创建上传Object的Metadata
    ObjectMetadata meta = new ObjectMetadata();

    // 必须设置ContentLength
    meta.setContentLength(bytes.length);
    //meta.setContentType("application/x-xls");

    // 上传Object.
    PutObjectResult result = client.putObject(bucketName, "smartad/" + uploadName, content, meta);

    // 打印ETag
    System.out.println(result.getETag());

    return true;
  }

  /**
   * 上传文件到OSS中
   *
   * @param filePath
   * @param uploadName
   * @return 是否上传成功
   * @throws IOException
   */
  public static Boolean uploadFileByPath(String filePath, String uploadName) throws IOException {
    OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    // 获取Bucket的存在信息
    boolean exists = client.doesBucketExist(bucketName);
    if (!exists) {
      // 新建一个Bucket
      client.createBucket(bucketName);
      //CannedAccessControlList是枚举类型，包含三个值： Private 、 PublicRead 、 PublicReadWrite
      client.setBucketAcl(bucketName, CannedAccessControlList.PublicReadWrite);
    }

    // 获取指定文件的输入流
    File file = new File(filePath);
    InputStream content = new FileInputStream(file);

    // 创建上传Object的Metadata
    ObjectMetadata meta = new ObjectMetadata();

    // 必须设置ContentLength
    meta.setContentLength(file.length());

    // 上传Object.
    PutObjectResult result = client.putObject(bucketName, uploadName, content, meta);

    // 打印ETag
    System.out.println(result.getETag());

    return true;
  }

  /**
   * 上传文件到OSS中
   *
   * @param url
   * @param uploadName
   * @return 是否上传成功
   * @throws IOException
   */
  public static Boolean uploadFileByUrl(String url, String uploadName) throws Exception {
    OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    // 获取Bucket的存在信息
    boolean exists = client.doesBucketExist(bucketName);
    if (!exists) {
      // 新建一个Bucket
      client.createBucket(bucketName);
      //CannedAccessControlList是枚举类型，包含三个值： Private 、 PublicRead 、 PublicReadWrite
      client.setBucketAcl(bucketName, CannedAccessControlList.PublicReadWrite);
    }

    // 获取指定文件的输入流

    URL urlContent = new URL(url);

    File file = new File(urlContent.toURI());

    InputStream content = new FileInputStream(file);

    // 创建上传Object的Metadata
    ObjectMetadata meta = new ObjectMetadata();

    // 必须设置ContentLength
    meta.setContentLength(file.length());

    // 上传Object.
    PutObjectResult result = client.putObject(bucketName, uploadName, content, meta);

    // 打印ETag
    System.out.println(result.getETag());

    return true;
  }

  /**
   * 通过文件名删除OSS中的文件
   *
   * @param uploadName 文件名称
   * @throws IOException
   */
  public static void deleteFile(String uploadName) throws IOException {
    OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    client.deleteObject(bucketName, uploadName);
  }

  /**
   * 计算文件总大小
   *
   * @param keysList
   * @return
   */
  public static Long calculateFileSize(List<String[]> keysList) {

    Long fileSize = 0L;

    OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    for (String[] keys : keysList) {
      String key = keys[0];
      OSSObject ossObject = client.getObject(bucketName, key);
      fileSize = fileSize + ossObject.getObjectMetadata().getContentLength();
    }

    return fileSize;
  }

  /**
   * 列出目录下的所有文件
   *
   * @param path 文件路径
   * @return
   */
  public static List listObjectByPath(String path) {

    OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);

    ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName);

    // 递归列出fun目录下的所有文件
    listObjectsRequest.setPrefix(path);

    ObjectListing listing = client.listObjects(listObjectsRequest);

    return listing.getObjectSummaries();
  }
}
