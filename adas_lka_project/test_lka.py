from pytest_bdd import scenarios, given, when, then, parsers

from platform_api import PlatformAPI
from LKA_Feature import LaneKeepAssist


scenarios("features/lane_keep_assist.feature")


@given(parsers.parse("the vehicle speed is {speed_kmh:d} km/h"), target_fixture="test_context")
def vehicle_speed(speed_kmh):
    platform = PlatformAPI()
    lka = LaneKeepAssist(platform)

    platform.set_property_values({
        "speed_kmh": speed_kmh
    })

    return {
        "platform": platform,
        "lka": lka
    }


@given(parsers.parse("the lane marking visibility is {lane_marking_visible}"))
def lane_marking_visibility(test_context, lane_marking_visible):
    test_context["platform"].set_property_values({
        "lane_marking_visible": lane_marking_visible == "true"
    })


@given(parsers.parse("the vehicle is drifting to the {drifting_direction}"))
def vehicle_drifting_direction(test_context, drifting_direction):
    test_context["platform"].set_property_values({
        "drifting_direction": drifting_direction
    })


@given(parsers.parse("the turn signal is {turn_signal_status}"))
def turn_signal_status(test_context, turn_signal_status):
    test_context["platform"].set_property_values({
        "turn_signal_status": turn_signal_status
    })


@when(parsers.parse("a new lane status {vehicle_lane_status} is received"))
def receive_lane_status(test_context, vehicle_lane_status):
    test_context["platform"].receive_lane_status(vehicle_lane_status)
    test_context["lka"].evaluate()


@then(parsers.parse("the LKA activation status should be {lka_should_activate}"))
def check_lka_activation(test_context, lka_should_activate):
    expected_activation = lka_should_activate == "true"

    assert test_context["platform"].get_lka_activation_status() == expected_activation


@then(parsers.parse("the requested steering correction should be {steering_correction}"))
def check_steering_correction(test_context, steering_correction):
    command = test_context["platform"].get_last_command()

    if steering_correction == "none":
        assert command is None
    else:
        assert command == {
            "action": "steering_correction",
            "direction": steering_correction
        }