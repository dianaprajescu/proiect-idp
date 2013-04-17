package Network;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

import app.UserType;

/**
 * Collection of users.
 * 
 * @author Stedy
 *
 */
public class UsersServer {
	ArrayList<Integer> ids;
	ArrayList<Integer> types;
	ArrayList<ArrayList<Integer>> services;
	ArrayList<SocketChannel> channels;
	
	/**
	 * Constructor.
	 */
	public UsersServer(){
		ids = new ArrayList<Integer>();
		types = new ArrayList<Integer>();
		channels = new ArrayList<SocketChannel>();
		services = new ArrayList<ArrayList<Integer>>();
	}
	
	/**
	 * New user gets online.
	 * 
	 * @param id
	 * @param type
	 * @param channel
	 */
	public void addUser(int id, Integer type, SocketChannel channel){
		ids.add(id);
		types.add(type);
		channels.add(channel);
		services.add(new ArrayList<Integer>());
	}
	
	/**
	 * User registers his service.
	 * 
	 * @param serviceId
	 * @param userId
	 * @throws IOException
	 */
	public void addService(int serviceId, int userId) throws IOException{
		
		// Find the index with the userId passed.
		int idx = ids.indexOf(userId);
		
		if (idx >= 0){
			
			// Add service.
			services.get(idx).add(serviceId);
			
			int type;
			
			// Change type to opposite.
			if (types.get(idx) == UserType.SELLER.getType()){
				type = UserType.BUYER.getType(); 
			}
			else{
				type = UserType.SELLER.getType();
			}
			
			// Go through user list and notify about getting online.
			for (int i = 0; i < ids.size(); i++){
				if (types.get(i) == type){
					for (int j = 0; j<services.get(i).size(); j++){
						
						// User has service with the current id.
						if (services.get(i).get(j) == serviceId){
							int[] message = {NetworkMethods.REGISTER_SERVICE.getInt(), serviceId, userId};
							
							StateWrite state = new StateWrite(channels.get(i), message);
							
							state.execute();
							
							break;
						}
					}
				}
			}
		}
	}
}
