package services;

import materials.EstateAgent;

public interface RegisterServiceIF {

    public boolean registerEstateAgent(EstateAgent agent);

    public boolean deleteEstateAgent(String loginName);

    public boolean updateEstateAgent(String newName, String newAddress, String loginName, String newassword);
}
