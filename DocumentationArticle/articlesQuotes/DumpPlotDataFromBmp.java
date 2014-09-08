		// iterate over image from left to right
		for (int x = 0; x < img.getWidth(); ++x) {
			
			// by default, the bottom pixel is a minimum
			y_min = img.getHeight();
			
			// white color (255) is minimum value, we expect black color (0) as max
			y_min_value = 255;
			
			// iterate image from top to bottom
			// the most-top black pixel is the minimum
			for(int y = 0; y < img.getHeight(); ++y) {
				tmp_value_color = new Color(img.getRGB(x, y));
				tmp_value = tmp_value_color.getRed();
				
				if(tmp_value < y_min_value) {
					y_min_value = tmp_value;
					y_min = y;
				}
			}
			
			// just because image (0,0) point is oriented in top-left, but not
			// bottom-left, we have to subtract height and y_min
			System.out.println(img.getHeight() - y_min);
		}