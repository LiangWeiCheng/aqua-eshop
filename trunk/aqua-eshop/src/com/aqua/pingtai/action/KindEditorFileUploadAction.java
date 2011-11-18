package com.aqua.pingtai.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

import com.aqua.pingtai.common.QueryParameter;
import com.aqua.pingtai.utils.Utils;

import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.json.simple.JSONObject;


@SuppressWarnings({"serial", "unchecked"})
public class KindEditorFileUploadAction extends BaseAction {
	
	private List<File> imgFile;//图片上传
	private List<String> uploadContentType;//上传文件类型
	private List<String> uploadFileName;//文件名
	private String id;  
    private String imgTitle;  
    private String imgWidth;  
    private String imgHeight;  
    private String imgBorder; 
    private String align;
    
    MultiPartRequestWrapper wrapper = null;
    
    private String imageUploadResultJSON;
    private String newImageName;
    private String fileManagerJSON;
    
	/**
	 * 处理图片上传
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String uploadImage(){
		if(null!=getImgFile()){
			wrapper = (MultiPartRequestWrapper) request;
			id = wrapper.getParameter("id");
			imgTitle = wrapper.getParameter("imgTitle");
			imgWidth = wrapper.getParameter("imgWidth");
			imgHeight = wrapper.getParameter("imgHeight");
			imgBorder = wrapper.getParameter("imgBorder");
			align = wrapper.getParameter("align");
			
			List<File> files = getImgFile();
			String savePath = request.getRealPath("/images/kindEditor");
			String imageOldName = getUploadFileName().get(0);//图片名称
			String houZhui = imageOldName.substring(getUploadFileName().get(0).lastIndexOf(".")+1);//后缀,不包含.
			
			StringBuffer imageName = new StringBuffer();
			imageName.append(Utils.getNowTimeString()).append("_").append(new Random().nextInt(9999999))
					 .append(".").append(houZhui);//文件名
			newImageName = imageName.toString();
			
			try {
				String osPath = Utils.getOsPathType();
				FileOutputStream fos = new FileOutputStream(savePath + osPath + imageName.toString());//"\\"
				FileInputStream fis = new FileInputStream(files.get(0));
				byte[] buffer = new byte[2048];
				int len = 0;
				while ((len = fis.read(buffer)) > 0)
				{
					fos.write(buffer , 0, len);
				}
				fos.close();
				
				JSONObject obj = new JSONObject();
				obj.put("error", 0);
				obj.put("url", request.getContextPath()+"/images/kindEditor/"+imageName.toString());
				
				imageUploadResultJSON = obj.toJSONString();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		returnPageURL = "/WEB-INF/jsp/jsFile/kindEditor/imageUploadResult.jsp";
		return "dispatcher";
	}
	
	/**
	 * 文件管理
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String imageFileManager() throws NumberFormatException{
		String osPath = Utils.getOsPathType();
		String rootPath = request.getRealPath("/images/kindEditor/") + osPath;
		String rootUrl = request.getContextPath() + "/images/kindEditor/";
		String[] fileTypes = new String[] { "gif", "jpg", "jpeg", "png", "bmp" };//图片扩展名

		//根据path参数，设置各路径和URL
		String path = request.getParameter("path") != null ? request.getParameter("path") : "";
		String currentPath = rootPath + path;
		String currentUrl = rootUrl + path;
		String currentDirPath = path;
		String moveupDirPath = "";
		if (!"".equals(path)) {
			String str = currentDirPath.substring(0, currentDirPath
					.length() - 1);
			moveupDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0,
					str.lastIndexOf("/") + 1) : "";
		}

		//排序形式，name or size or type
		String order = request.getParameter("order") != null ? request
				.getParameter("order").toLowerCase() : "name";

		//不允许使用..移动到上一级目录
		if (path.indexOf("..") >= 0) {
			//out.println("Access is not allowed.");
			//return;
		}
		//最后一个字符不是/
		if (!"".equals(path) && !path.endsWith("/")) {
			//out.println("Parameter is not valid.");
			//return;
		}
		//目录不存在或不是目录
		File currentPathFile = new File(currentPath);
		if (!currentPathFile.isDirectory()) {
			//out.println("Directory does not exist.");
			//return;
		}
		
		//遍历目录取的文件信息
		List<Hashtable> fileList = new ArrayList<Hashtable>();
		if (currentPathFile.listFiles() != null) {
			for (File file : currentPathFile.listFiles()) {
				Hashtable<String, Object> hash = new Hashtable<String, Object>();
				String fileName = file.getName();
				if (file.isDirectory()) {
					hash.put("is_dir", true);
					hash.put("has_file", (file.listFiles() != null));
					hash.put("filesize", 0L);
					hash.put("is_photo", false);
					hash.put("filetype", "");
				} else if (file.isFile()) {
					String fileExt = fileName.substring(
							fileName.lastIndexOf(".") + 1).toLowerCase();
					hash.put("is_dir", false);
					hash.put("has_file", false);
					hash.put("filesize", file.length());
					hash.put("is_photo", Arrays.<String> asList(fileTypes)
							.contains(fileExt));
					hash.put("filetype", fileExt);
				}
				hash.put("filename", fileName);
				hash.put("datetime", new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
				fileList.add(hash);
			}
		}

		if ("size".equals(order)) {
			Collections.sort(fileList, new SizeComparator());
		} else if ("type".equals(order)) {
			Collections.sort(fileList, new TypeComparator());
		} else {
			Collections.sort(fileList, new NameComparator());
		}
		JSONObject result = new JSONObject();
		result.put("moveup_dir_path", moveupDirPath);
		result.put("current_dir_path", currentDirPath);
		result.put("current_url", currentUrl);
		result.put("total_count", fileList.size());
		result.put("file_list", fileList);

		fileManagerJSON = result.toJSONString();
		
		returnPageURL = "/WEB-INF/jsp/jsFile/kindEditor/imageManager.jsp";
		return "dispatcher";
	}

	@Override
	protected String getActionClassFullName() {
		// TODO Auto-generated method stub
		return "com.aqua.pingtai.action.pingtai.KindEditorFileUploadAction.java";
	}

	@Override
	protected String makeFilterString(QueryParameter queryParameter) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<File> getImgFile() {
		return imgFile;
	}

	public void setImgFile(List<File> imgFile) {
		this.imgFile = imgFile;
	}

	public List<String> getUploadContentType() {
		String[] contentTypes =wrapper.getContentTypes("imgFile");//获得上传的文件类型
		int length = contentTypes.length;
		uploadContentType = new ArrayList<String>(length);
		for (String contentType : contentTypes) {
			uploadContentType.add(contentType);
		}
		return uploadContentType;
	}

	public void setUploadContentType(List<String> uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public List<String> getUploadFileName() {
		String[] fileNames = wrapper.getFileNames("imgFile");//获得上传的文件名
		int length = wrapper.getFileNames("imgFile").length;
		uploadFileName = new ArrayList<String>(length);
		for (String fileName : fileNames) {
			uploadFileName.add(fileName);
		}
		return uploadFileName;
	}

	public void setUploadFileName(List<String> uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImgTitle() {
		return imgTitle;
	}

	public void setImgTitle(String imgTitle) {
		this.imgTitle = imgTitle;
	}

	public String getImgWidth() {
		return imgWidth;
	}

	public void setImgWidth(String imgWidth) {
		this.imgWidth = imgWidth;
	}

	public String getImgHeight() {
		return imgHeight;
	}

	public void setImgHeight(String imgHeight) {
		this.imgHeight = imgHeight;
	}

	public String getImgBorder() {
		return imgBorder;
	}

	public void setImgBorder(String imgBorder) {
		this.imgBorder = imgBorder;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public String getImageUploadResultJSON() {
		return imageUploadResultJSON;
	}

	public void setImageUploadResultJSON(String imageUploadResultJSON) {
		this.imageUploadResultJSON = imageUploadResultJSON;
	}

	public String getNewImageName() {
		return newImageName;
	}

	public void setNewImageName(String newImageName) {
		this.newImageName = newImageName;
	}

	public MultiPartRequestWrapper getWrapper() {
		return wrapper;
	}

	public void setWrapper(MultiPartRequestWrapper wrapper) {
		this.wrapper = wrapper;
	}

	public String getFileManagerJSON() {
		return fileManagerJSON;
	}

	public void setFileManagerJSON(String fileManagerJSON) {
		this.fileManagerJSON = fileManagerJSON;
	}

	public class NameComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable) a;
			Hashtable hashB = (Hashtable) b;
			if (((Boolean) hashA.get("is_dir"))
					&& !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir"))
					&& ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String) hashA.get("filename"))
						.compareTo((String) hashB.get("filename"));
			}
		}
	}
	
	public class SizeComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable) a;
			Hashtable hashB = (Hashtable) b;
			if (((Boolean) hashA.get("is_dir"))
					&& !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir"))
					&& ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				if (((Long) hashA.get("filesize")) > ((Long) hashB
						.get("filesize"))) {
					return 1;
				} else if (((Long) hashA.get("filesize")) < ((Long) hashB
						.get("filesize"))) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}

	public class TypeComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable) a;
			Hashtable hashB = (Hashtable) b;
			if (((Boolean) hashA.get("is_dir"))
					&& !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir"))
					&& ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String) hashA.get("filetype"))
						.compareTo((String) hashB.get("filetype"));
			}
		}
	}

}
