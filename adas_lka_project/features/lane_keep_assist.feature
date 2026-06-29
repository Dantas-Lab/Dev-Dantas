Feature: Lane Keep Assist Operational Design Domain

  Scenario Outline: Validate LKA activation based on speed range and ODD conditions
    Given the vehicle speed is <speed_kmh> km/h
    And the lane marking visibility is <lane_marking_visible>
    And the vehicle is drifting to the <drifting_direction>
    And the turn signal is <turn_signal_status>
    When a new lane status <vehicle_lane_status> is received
    Then the LKA activation status should be <lka_should_activate>
    And the requested steering correction should be <steering_correction>

    Examples:
      | speed_kmh | lane_marking_visible | drifting_direction | turn_signal_status | lka_should_activate | vehicle_lane_status |steering_correction |
      | 0         | true                 | left               | off                | false               | out                 | none               |
      | 10        | true                 | left               | off                | false               | out                 | none               |
      | 20        | true                 | left               | off                | false               | out                 | none               |
      | 30        | true                 | left               | off                | false               | out                 | none               |
      | 40        | true                 | left               | off                | false               | out                 | none               |
      | 50        | true                 | left               | off                | false               | out                 | none               |
      | 60        | true                 | left               | off                | true                | out                 | right              |
      | 70        | true                 | left               | off                | true                | out                 | right              |
      | 80        | true                 | left               | off                | true                | out                 | right              |
      | 90        | true                 | left               | off                | true                | out                 | right              |
      | 100       | true                 | left               | off                | true                | out                 | right              |
      | 70        | false                | left               | off                | false               | out                 | none               |
      | 70        | true                 | left               | on                 | false               | out                 | none               |
      | 70        | true                 | none               | off                | false               | out                 | none               |