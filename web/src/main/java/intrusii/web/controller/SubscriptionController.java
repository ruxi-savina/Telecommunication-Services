package intrusii.web.controller;

import intrusii.core.model.Subscription;
import intrusii.core.model.SubscriptionType;
import intrusii.core.service.SubscriptionService;
import intrusii.web.converter.SubscriptionConverter;
import intrusii.web.dto.SubscriptionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/subscriptions")
@RestController
public class SubscriptionController {
    public static final Logger log = LoggerFactory.getLogger(SubscriptionController.class);

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private SubscriptionConverter subscriptionConverter;

    @RequestMapping(value = "", method = RequestMethod.POST)
    SubscriptionDto addSubscription(@RequestBody SubscriptionDto subscriptionDto){
        log.trace("addSubscription - method entered: subscription={}", subscriptionDto);

        var subscription = subscriptionConverter.convertDtoToModel(subscriptionDto);
        var result = subscriptionService.addSubscription(subscription);
        var resultModel = subscriptionConverter.convertModelToDto(result);

        log.trace("updateSubscription - method finished : subscription={}", result);
        return resultModel;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteSubscription(@PathVariable Long id) {
        log.trace("deleteSubscription - method entered: id={}", id);

        subscriptionService.deleteSubscription(id);

        log.trace("deleteSubscription - method finished");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    SubscriptionDto updateSubscription(@PathVariable Long id, @RequestBody SubscriptionDto dto) {
        log.trace("updateSubscription - method entered: subscription={}", dto);

        SubscriptionDto subscriptionDto = subscriptionConverter.convertModelToDto(
                subscriptionService.updateSubscription(
                        subscriptionConverter.convertDtoToModel(dto)));

        log.trace("updateSubscription - method finished: subscription={}", subscriptionDto);
        return subscriptionDto;
    }

    @RequestMapping(value = "")
    List<SubscriptionDto> getAllSubscriptions() {
        log.trace("getAllSubscriptions - method entered");

        List<Subscription> subscriptions = subscriptionService.getAllSubscriptions();

        log.trace("getAllSubscriptions - method finished: subscriptions={}", subscriptions);
        return new ArrayList<>(subscriptionConverter.convertModelsToDtos(subscriptions));
    }

    @RequestMapping(value = "/byId", method = RequestMethod.POST)
    SubscriptionDto getSubscriptionById(@RequestBody Long id) {
        log.trace("getSubscriptionById - method entered: id={}", id);

        SubscriptionDto subscription = subscriptionConverter.convertModelToDto(
                subscriptionService.getSubscriptionById(id));

        log.trace("getSubscriptionById - method finished: subscription={}", subscription);
        return subscription;
    }

    @RequestMapping(value = "/filterByDuration", method = RequestMethod.POST)
    List<SubscriptionDto> filterSubscriptionByDuration(@RequestBody int duration) {
        log.trace("filterSubscriptionByDuration - method entered");

        List<Subscription> subscriptions = subscriptionService.filterSubscriptionByDuration(duration);

        log.trace("filterSubscriptionByDuration - method finished: subscriptions={}", subscriptions);
        return new ArrayList<>(subscriptionConverter.convertModelsToDtos(subscriptions));
    }

    @RequestMapping(value = "/filterByType", method = RequestMethod.POST)
    List<SubscriptionDto> filterSubscriptionByType(@RequestBody SubscriptionType type) {
        log.trace("filterSubscriptionByType - method entered");

        List<Subscription> subscriptions = subscriptionService.filterSubscriptionByType(type);

        log.trace("filterSubscriptionByType - method finished: subscriptions={}", subscriptions);
        return new ArrayList<>(subscriptionConverter.convertModelsToDtos(subscriptions));
    }
}
