//
//import java.util.ArrayList; 
//import java.util.HashMap; 
//import java.util.List; 
//import java.util.Map; 
//import java.util.Set; 
//import java.util.Map.Entry; 
//
///* 
//U have an organizational structure, which shows hierarchy of the organization. This hierarchy contains employees E or managers M who has some Employees or Managers reporting to M. 
//Employee has ( id, name, JobDesc, salary etc). 
//Design the data structure you would be using to store this hierarchy 
//
//problem 1: Given an ID of an employee , print all the employee ID's who are directly reporting or indirectly reporting to the manager. 
//
//problem 2: prefix search of employees by String. If employees have nishant and nikhil. If searched by "ni" we need to print all details of both nishant and nikhil. 
//
//problem 3(bonus): search should print all emloyee's and their details if a given string is subString of the name of an employee.(Like a phonebook contacts search) 
//*/ 
//
//public class OrganizationStructure { 
//private static Map<Integer, Employee> data = new HashMap<Integer, Employee>(); 
//
//public static void main(String[] args) { 
//
//Employee emp = new Employee(); 
//emp.setId(5); 
//emp.setName("sachine"); 
//emp.setJobDesc("SPM"); 
//emp.setSal(1000000.00); 
//emp.setReportedEmployees(new ArrayList<Employee>()); 
//storeData(emp, 5); 
//
//emp = new Employee(); 
//emp.setId(12); 
//emp.setName("sachine"); 
//emp.setJobDesc("SPM"); 
//emp.setSal(1000000.00); 
//emp.setReportedEmployees(new ArrayList<Employee>()); 
//storeData(emp, 5); 
//
//emp = new Employee(); 
//emp.setId(1); 
//emp.setName("Praveen"); 
//emp.setJobDesc("SSE"); 
//emp.setSal(36000.00); 
//emp.setManagerId(5); 
//emp.setReportedEmployees(new ArrayList<Employee>()); 
//storeData(emp, 1); 
//
//emp = new Employee(); 
//emp.setId(2); 
//emp.setName("nure"); 
//emp.setJobDesc("SSE"); 
//emp.setSal(36000.00); 
//emp.setManagerId(5); 
//emp.setReportedEmployees(new ArrayList<Employee>()); 
//storeData(emp, 2); 
//
//emp = new Employee(); 
//emp.setId(3); 
//emp.setName("garima"); 
//emp.setJobDesc("SSE"); 
//emp.setSal(36000.00); 
//emp.setManagerId(5); 
//emp.setReportedEmployees(new ArrayList<Employee>()); 
//storeData(emp, 3); 
//
//emp = new Employee(); 
//emp.setId(4); 
//emp.setName("sub"); 
//emp.setJobDesc("SP"); 
//emp.setSal(76000.00); 
//emp.setManagerId(5); 
//emp.setReportedEmployees(new ArrayList<Employee>()); 
//storeData(emp, 4); 
//
//emp = new Employee(); 
//emp.setId(6); 
//emp.setName("emp1"); 
//emp.setJobDesc("SSE"); 
//emp.setSal(36000.00); 
//emp.setManagerId(4); 
//emp.setReportedEmployees(new ArrayList<Employee>()); 
//storeData(emp, 6); 
//
//emp = new Employee(); 
//emp.setId(7); 
//emp.setName("Pradfgveen"); 
//emp.setJobDesc("SSE"); 
//emp.setSal(36000.00); 
//emp.setManagerId(4); 
//emp.setReportedEmployees(new ArrayList<Employee>()); 
//storeData(emp, 7); 
//
//emp = new Employee(); 
//emp.setId(8); 
//emp.setName("Pradfveen"); 
//emp.setJobDesc("SSE"); 
//emp.setSal(36000.00); 
//emp.setManagerId(4); 
//emp.setReportedEmployees(new ArrayList<Employee>()); 
//Map<Integer, Employee> data = storeData(emp, 8); 
//
//emp = new Employee(); 
//emp.setId(9); 
//emp.setName("Praveen"); 
//emp.setJobDesc("SSE"); 
//emp.setSal(36000.00); 
//emp.setManagerId(8); 
//emp.setReportedEmployees(new ArrayList<Employee>()); 
//storeData(emp, 9); 
//
//emp = new Employee(); 
//emp.setId(10); 
//emp.setName("Praveen"); 
//emp.setJobDesc("SSE"); 
//emp.setSal(36000.00); 
//emp.setReportedEmployees(new ArrayList<Employee>()); 
//storeData(emp, 9); 
//
//System.out.println(getReportedEmployees(4, data)); 
//searchPrifixNames("Pra"); 
//searchSubNames("rim"); 
//
//} 
//
//private static void searchPrifixNames(String str) { 
//Set<Entry<Integer, Employee>> empSet = data.entrySet(); 
//for (Entry<Integer, Employee> entry : empSet) { 
//entry.getValue(); 
//if (entry.getValue().getName().startsWith(str)) { 
//System.out.println(entry.getValue().toString()); 
//} 
//} 
//
//} 
//
//private static void searchSubNames(String str) { 
//Set<Entry<Integer, Employee>> empSet = data.entrySet(); 
//for (Entry<Integer, Employee> entry : empSet) { 
//entry.getValue(); 
//if (entry.getValue().getName().contains(str)) { 
//System.out.println(entry.getValue().toString()); 
//} 
//} 
//
//} 
//
//private static Map<Integer, Employee> storeData(Employee emp, int parseInt) { 
//
//data.put(parseInt, emp); 
//
//Employee employee = data.get(emp.getManagerId()); 
//if (employee != null) { 
//employee.getReportedEmployees().add(emp); 
//} 
//return data; 
//} 
//
//private static List<Integer> getReportedEmployees(int id, 
//Map<Integer, Employee> data) { 
//List<Integer> empListIds = new ArrayList<Integer>(); 
//Employee employee = data.get(id); 
//
//if (employee != null) { 
//recursiveMethod(data, empListIds, employee.getReportedEmployees()); 
//} 
//
//return empListIds; 
//} 
//
//private static void recursiveMethod(Map<Integer, Employee> data, 
//List<Integer> empListIds, List<Employee> listEmp) { 
//for (Employee emp : listEmp) { 
//empListIds.add(emp.getId()); 
//Employee temp = data.get(emp.getId()); 
//if (temp != null) { 
//recursiveMethod(data, empListIds, temp.getReportedEmployees()); 
//} 
//} 
//} 
//}
//
// class Employee { 
//private int id; 
//private String name; 
//private String jobDesc; 
//private double sal; 
//private int managerId; 
//private List<Employee> reportedEmployees; 
//
//public int getId() { 
//return id; 
//} 
//
//public void setId(int id) { 
//this.id = id; 
//} 
//
//public String getName() { 
//return name; 
//} 
//
//public void setName(String name) { 
//this.name = name; 
//} 
//
//public String getJobDesc() { 
//return jobDesc; 
//} 
//
//public void setJobDesc(String jobDesc) { 
//this.jobDesc = jobDesc; 
//} 
//
//public double getSal() { 
//return sal; 
//} 
//
//public void setSal(double sal) { 
//this.sal = sal; 
//} 
//
//public int getManagerId() { 
//return managerId; 
//} 
//
//public void setManagerId(int managerId) { 
//this.managerId = managerId; 
//} 
//
//public List<Employee> getReportedEmployees() { 
//return reportedEmployees; 
//} 
//
//public void setReportedEmployees(List<Employee> reportedEmployees) { 
//this.reportedEmployees = reportedEmployees; 
//} 
//
//@Override 
//public String toString() { 
//return "{id=" + id + ", name=" + name + ", job description=" + jobDesc 
//+ ", sal=" + sal + "}"; 
//} 
//}
