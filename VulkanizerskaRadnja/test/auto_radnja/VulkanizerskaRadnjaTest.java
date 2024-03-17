package auto_radnja;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VulkanizerskaRadnjaTest extends RadnjaTest {

	

	@Override
	public Radnja getInstance() {
		return new VulkanizerskaRadnja();
	}

}
