import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class orgChart {
	private static Map<Integer, Employee> data = new HashMap<Integer, Employee>();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		final int portNumber = 8090;
		String FileName = "/Users/roshaninagmote/Documents/workspace/OrgChartServer/src/sample.txt";
		System.out.println("Creating server socket on port " + portNumber);
		
		ServerSocket serverSocket = new ServerSocket(portNumber);
		while (true) {
			Socket socket = serverSocket.accept();
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os, true);
			pw.println("Get ?");
			 
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String name = br.readLine();
			BufferedReader bf = new BufferedReader(new FileReader(FileName));
			
			String line = null;
			while ((line = bf.readLine()) != null) {
				String[] str = line.split(":");
				
				if(str.length == 3){
					Employee emp = new Employee();
				emp.setId(Integer.parseInt(str[0].trim()));
				emp.setName((str[1].trim()));
				emp.setManagerId(Integer.parseInt(str[2].trim()));
				emp.setReportees(new ArrayList<Employee>()); 
				storeData(emp, Integer.parseInt(str[0].trim())); 
				}
				else if(str.length == 1){
				}
				else
					System.out.println("Number of tokens is not equal to 3");
					
			}
			int search_id = searchId(name);
			if(search_id!=-99)
			{
				System.out.println("Reports to: "+getReportsTo(search_id, data));
				System.out.println("Reportees: "+ getReportees(search_id, data)); 
			}
			
			else
				System.out.println("Name does not exist");
			
			pw.println("Get " + name);
			pw.close();
			socket.close();
		}
		}
	
	private static Map<Integer, Employee> storeData(Employee emp, int parseInt) { 

		data.put(parseInt, emp); 

		Employee employee = data.get(emp.getManagerId()); 
		if (employee != null) { 
		employee.getReportees().add(emp); 
		} 
		return data; 
		}
	
	private static int searchId(String str) { 
		Set<Entry<Integer, Employee>> empSet = data.entrySet(); 
		for (Entry<Integer, Employee> entry : empSet) { 
		entry.getValue(); 
		if (entry.getValue().getName().equals(str)) { 
		return entry.getValue().getId();
		} 
		} 
		return -99;
		} 
	private static List<String> getReportees(int id, 
			Map<Integer, Employee> data) { 
			List<String> empListNames = new ArrayList<String>();
			Employee employee = data.get(id); 
			
			if (employee != null) {  
			List<Employee> listEmp = employee.getReportees();
			for (Employee emp : listEmp) { 
				empListNames.add(emp.getName());
				Employee temp = data.get(emp.getId()); 
				} 
			} 
			
			return empListNames; 
			} 

	private static String getReportsTo(int id, Map<Integer, Employee> data) { 
			List<Integer> empListIds = new ArrayList<Integer>(); 
			int reportTo;
			Employee employee = data.get(id); 
			
			if (employee != null) { 
				Employee employee_manager = data.get(employee.getManagerId());
				return employee_manager.getName();
			} 
			
			return ""; 
			} 

}

class Employee { 
private int id; 
private String name; 
private int managerId; 
private List<Employee> reportees; 

public int getId() { 
return id; 
} 

public void setId(int id) { 
this.id = id; 
} 

public String getName() { 
return name; 
} 

public void setName(String name) { 
this.name = name; 
} 

public int getManagerId() { 
return managerId; 
} 

public void setManagerId(int managerId) { 
this.managerId = managerId; 
} 

public List<Employee> getReportees() { 
return reportees; 
} 

public void setReportees(List<Employee> reportees) { 
this.reportees = reportees; 
} 

@Override 
public String toString() { 
return "{id=" + id + ", name=" + name + "}"; 
} 
}
