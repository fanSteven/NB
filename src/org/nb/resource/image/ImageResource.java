package org.nb.resource.image;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

@Path("/images")
@Consumes(MediaType.MULTIPART_FORM_DATA)
public class ImageResource {
	private static final String SERVER_UPLOAD_LOCATION_FOLDER = "C://";

	private static Logger logger = Logger.getLogger(ImageResource.class);

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String uploadStatePolicy(@Context HttpServletRequest request) {
		try {
			String fileName = saveFile(request);
			if (!fileName.equals("")) {
			} else {

			}
		} catch (Exception ex) {
			logger.error("上传文件失败", ex);
		}
		return "";
	}

	/**
	 * @Description: 保存文件92101127
	 * @param request
	 *            请求体
	 * @return 文件名
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	private String saveFile(HttpServletRequest request) {
		String fileName = "";
		try {
			if (ServletFileUpload.isMultipartContent(request)) {
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				List<FileItem> items = null;
				try {
					items = upload.parseRequest(request);
				} catch (FileUploadException e) {
					e.printStackTrace();
				}
				if (items != null) {
					Iterator<FileItem> iter = items.iterator();
					while (iter.hasNext()) {
						FileItem item = iter.next();
						if (!item.isFormField() && item.getSize() > 0) {
							fileName = processFileName(item.getName());
							try {
								item.write(new File(
										SERVER_UPLOAD_LOCATION_FOLDER
												+ fileName));
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("save file error", e);
		}
		return fileName;
	}

	/**
	 * @Description: 文件名称截取
	 * @param fileNameInput
	 *            请求体的文件名
	 * @return 文件名
	 * @throws
	 */
	private String processFileName(String fileNameInput) {
		String fileNameOutput = null;
		fileNameOutput = fileNameInput.substring(
				fileNameInput.lastIndexOf("\\") + 1, fileNameInput.length());
		return fileNameOutput;
	}
}
