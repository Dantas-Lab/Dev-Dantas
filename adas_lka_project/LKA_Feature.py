class LaneKeepAssist:
    def __init__(self, platform_api):
        self.platform_api = platform_api

    def evaluate(self):
        state = self.platform_api.get_property_values()

        speed = state.get("speed_kmh")
        lane_visible = state.get("lane_marking_visible")
        drifting_direction = state.get("drifting_direction")
        turn_signal = state.get("turn_signal_status")
        vehicle_lane_status = state.get("vehicle_lane_status")

        if (
            speed >= 60
            and lane_visible
            and vehicle_lane_status == "out"
            and drifting_direction == "left"
            and turn_signal == "off"
        ):
            self.platform_api.set_lka_activation_status(True)
            self.platform_api.request_steering_correction("right")

        elif (
            speed >= 60
            and lane_visible
            and vehicle_lane_status == "out"
            and drifting_direction == "right"
            and turn_signal == "off"
        ):
            self.platform_api.set_lka_activation_status(True)
            self.platform_api.request_steering_correction("left")

        else:
            self.platform_api.set_lka_activation_status(False)