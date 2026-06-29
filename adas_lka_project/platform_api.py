class PlatformAPI:
    def __init__(self):
        self.vehicle_state = {}
        self.last_command = None
        self.lka_active = False

    def set_property_values(self, properties):
        self.vehicle_state.update(properties)

    def receive_lane_status(self, lane_status):
        self.vehicle_state["vehicle_lane_status"] = lane_status

    def get_property_values(self):
        return self.vehicle_state

    def set_lka_activation_status(self, is_active):
        self.lka_active = is_active

    def get_lka_activation_status(self):
        return self.lka_active

    def request_steering_correction(self, direction):
        self.last_command = {
            "action": "steering_correction",
            "direction": direction
        }

    def get_last_command(self):
        return self.last_command