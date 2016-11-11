package product;

import java.util.HashSet;

enum MachineType {
	COFFEE_MACHINE_LARGE("large machine"),
	COFFEE_MACHINE_SMALL("small machine"),
	ESPRESSO_MACHINE("espresso machine");
	
	private String value;
	MachineType(String value)
	{
		this.value = value;
	}
	
	protected String GetValue()
	{
		return this.value;
	}
}

public class Machine {
	private String _machineType;
	private boolean _isWaterLineCompatible;
	private String _machineId;
	private String _machineModel;
	private HashSet<Pod> _machinePods;
	public Machine(String machineId, String machineType, String machineModel, boolean isWaterLineCompatible)
	{
		_machineId = machineId;
		_machineType = machineType;
		_machineModel = machineModel;
		_isWaterLineCompatible = isWaterLineCompatible;
		_machinePods = new HashSet<>();
	}
	
	public String GetMachineId()
	{
		return _machineId;
	}
	
	public String GetMachineType()
	{
		return _machineType;
	}
	
	public String GetModel()
	{
		return _machineModel;
	}
	
	public HashSet<Pod> GetPods()
	{
		return _machinePods;
	}
	
	public boolean isWaterLineCompatible()
	{
		return _isWaterLineCompatible;
	}
	
	public void AddPod(Pod pod)
	{
		_machinePods.add(pod);
	}
	
	public boolean isPodTypeCompatible(Pod pod)
	{
		return pod.GetMachineType().equals(_machineType);
	}
}
