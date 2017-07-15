package weight.manager;

public class weight {
	
	private long mId;
	private long mDate;
	private String mWeight;
	private long mWaistSize;
	private long mChest;
	private long mHip;
	private long mNeck;
	


	public weight(long Date) {
		this.mDate = Date;
	}	
	
	public weight(long id, long Date, String Weight, long WaistSize, long Chest, long Hip, long Neck) {
		this.mId = id;
		this.mDate = Date;
		this.mWeight = Weight;
		this.mWaistSize = WaistSize;
		this.mChest = Chest;
		this.mHip = Hip;
		this.mNeck = Neck;
	}
	
	public weight(long date, String Weight, long WaistSize, long Chest, long Hip, long Neck) {
		this.mDate = date;
		this.mWeight = Weight;
		this.mWaistSize = WaistSize;
		this.mChest = Chest;
		this.mHip = Hip;
		this.mNeck = Neck;
	}

	public long getId() {
		return mId;
	}
	
	public long getDate() {
		return mDate;
	}
	
	public String getWeight() {
		return mWeight;
	}
	
	public long getWaistSize() {
		return mWaistSize;
	}
	
	public long getChest() {
		return mChest;
	}
	
	public long getHip() {
		return mHip;
	}
	
	public long getNeck() {
		return mNeck;
	}

	
	
	public void setId(long Id) {
		this.mId = Id;
	}

    public void setDate(long Date){
    	this.mDate = Date;
    }

    public void setWeight(String Weight){
    	this.mWeight = Weight;
    }
    
	public void setWaistSize(long WaistSize) {
		this.mWaistSize = WaistSize;
	}
	
	public void setChest(long Chest) {
		this.mChest = Chest;
	}
	
	public void setHip(long Hip) {
		this.mHip = Hip;
	}
	
	public void setNeck(long Neck) {
		this.mNeck = Neck;
	}


}
