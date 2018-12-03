@RequestMapping("/getImage.do")
	public void getImage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		 System.out.println("#######################�������ֺ���ĸ����֤��#######################");  
	        BufferedImage img = new BufferedImage(68, 22,  
	  
	        BufferedImage.TYPE_INT_RGB);  
	  
	        // �õ���ͼƬ�Ļ�ͼ����    
	  
	        Graphics g = img.getGraphics();  
	  
	        Random r = new Random();  
	  
	        Color c = new Color(200, 150, 255);  
	  
	        g.setColor(c);  
	  
	        // �������ͼƬ����ɫ    
	  
	        g.fillRect(0, 0, 68, 22);  
	  
	        // ��ͼƬ��������ֺ���ĸ    
	  
	        StringBuffer sb = new StringBuffer();  
	  
	        char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();  
	  
	        int index, len = ch.length;  
	  
	        for (int i = 0; i < 4; i++) {  
	  
	            index = r.nextInt(len);  
	  
	            g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt  
	  
	            (255)));  
	  
	            g.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));  
	            // �����  ����ʹ�С                      
	  
	            g.drawString("" + ch[index], (i * 15) + 3, 18);  
	            //дʲô���֣���ͼƬ ��ʲôλ�û�    
	  
	            sb.append(ch[index]);  
	  
	        }  
	  
	        request.getSession().setAttribute("piccode", sb.toString());  
	  
	        ImageIO.write(img, "JPG", response.getOutputStream());  
	}
