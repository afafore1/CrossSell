package product;

import java.util.ArrayList;

public class Pod {
	enum PodType
	{
		// give machine type as string
		COFFEE_POD_LARGE("large coffee pod"),
		COFFEE_POD_SMALL("small coffee pod"),
		ESPRESSO_POD("espresso pod");
		
		private String value;
		PodType(String value)
		{
			this.value = value;
		}
		
		protected String GetPodType()
		{
			return this.value;
		}
	}
	
	public enum CoffeeFlavor
	{
		COFFEE_FLAVOR_VANILLA("vanilla"),
		COFFEE_FLAVOR_CARAMEL("caramel"),
		COFFEE_FLAVOR_PSL("psl"),
		COFFEE_FLAVOR_MOCHA("mocha"),
		COFFEE_FLAVOR_HAZELNUT("hazelnut");
		private String value;
		CoffeeFlavor(String value)
		{
			this.value = value;
		}
		
		public String GetCoffeeFlavor()
		{
			return this.value;
		}
	};

	public enum PackSize
	{
		ONEDOZEN ("1 dozen"),
		THREEDOZEN("3 dozen"),
		FIVEDOZEN("5 dozen"),
		SEVENDOZEN("7 dozen");
		private String value;
		PackSize(String value)
		{
			this.value = value;
		}
		
		public String GetSizeValue()
		{
			return this.value;
		}
	}
	private String _podId;
	private String _podType;
	private CoffeeFlavor _flavor;
	private PackSize _size;
	private ArrayList<CoffeeFlavor> _coffeeFlavors;
	
	public Pod(String podId, String podType, PackSize size, CoffeeFlavor flavor)
	{
		_podId = podId;
		_podType = podType;
		_flavor = flavor;
		_size = size;
		_coffeeFlavors = new ArrayList<>();
	}
	
	public String GetPodId()
	{
		return _podId;
	}
	
	public String GetPodType()
	{
		return _podType;
	}
	// returns machine pod should belong to
	protected String GetMachineType()
	{
		PodType podType = GetPodType(_podType);
		if(podType != null)
		{
			switch(podType)
			{
			case COFFEE_POD_LARGE:
				return "large machine";
			case COFFEE_POD_SMALL:
				return "small machine";
			case ESPRESSO_POD:
				return "espresso machine";
				default:
					return null;
			}			
		}
		return null;
	}
	
	private PodType GetPodType(String podValue)
	{
		PodType [] podTypes = PodType.values();
		for(PodType podType : podTypes)
		{
			if(podType.GetPodType().equals(podValue))
			{
				return podType;
			}
		}
		return null;
	}
	
	public String GetFlavor()
	{
		return _flavor.GetCoffeeFlavor();
	}
	
	public PackSize GetSize()
	{
		return _size;
	}
}
