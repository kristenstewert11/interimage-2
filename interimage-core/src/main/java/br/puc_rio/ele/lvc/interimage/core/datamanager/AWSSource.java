/*Copyright 2014 Computer Vision Lab

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package br.puc_rio.ele.lvc.interimage.core.datamanager;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;

import br.puc_rio.ele.lvc.interimage.data.Image;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;

/**
 * A Source class that communicates with Amazon S3. 
 * @author Rodrigo Ferreira
 */
@SuppressWarnings("unused")
public class AWSSource implements Source {

	private String _accessKey;	
	private String _secretKey;
	private String _bucket;
	
	public AWSSource(String accessKey, String secretKey, String bucket) {
		_accessKey = accessKey;
		_secretKey = secretKey;
		_bucket = bucket;
	}
	
	public void put(String from, String to, Resource resource) {

		try {
		
			AWSCredentials credentials = new BasicAWSCredentials(_accessKey, _secretKey);
			AmazonS3 conn = new AmazonS3Client(credentials);
			conn.setEndpoint("https://s3.amazonaws.com");
								
			//conn.setBucketAcl(_bucket, CannedAccessControlList.PublicRead);
			
			//FileInputStream stream = new FileInputStream(from);
			File file = new File(from);
			
			//conn.putObject(_bucket, to, stream, new ObjectMetadata());
				
			PutObjectRequest putObjectRequest = new PutObjectRequest(_bucket, to, file);
			putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead); // public for all
			
			TransferManager tx = new TransferManager(credentials);
			Upload myUpload = tx.upload(putObjectRequest);
			
			/*ObjectMetadata data = new ObjectMetadata();
			data.setContentType("application/octet-stream");
			putObjectRequest.setMetadata(data);*/
			
			/*if (resource instanceof SplittableResource) {
				SplittableResource rsrc = (SplittableResource)resource;
				if (rsrc.getType() == SplittableResource.IMAGE) {
					Image image = (Image)rsrc.getObject();
					//TODO: support other formats
					if ((image.getURL().endsWith(".tif")) || (image.getURL().endsWith(".tiff"))) {
						ObjectMetadata data = new ObjectMetadata();
						data.setContentType("image/tiff");
						putObjectRequest.setMetadata(data);
					}
				}
			}*/
			
			//conn.putObject(putObjectRequest);
			
			//System.out.println("Should upload this file: " + to);
			
		} catch (Exception e) {
			System.err.println("Source put failed: " + e.getMessage());			
		}
		
	}
	
	public String getURL() {
		return "https://s3.amazonaws.com/" + _bucket + "/";
	}
	
}
