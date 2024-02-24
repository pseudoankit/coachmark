package com.pseudoankit.coachmark.model

import androidx.compose.runtime.Immutable
import com.pseudoankit.coachmark.scope.CoachMarkScope

/**
 * Click event to perform when clicked on overlay when any tooltip is active
 * or coachmark is visible
 *
 * @see CoachMarkScope.enableCoachMark to enable coachmark
 * @see [CoachMarkScope.show] to show coachmark
 * @see [CoachMarkScope.hide] to hide a coachmark
 */
@Immutable
public enum class OverlayClickEvent {

    /**
     * Event denoting that on click on overlay, it would switch to previous item
     * If nothing on previous then it will dismiss
     * @see [CoachMarkScope.show]
     */
    GoPrevious,

    /**
     * Event denoting that on click on overlay, it would switch to next item
     * If nothing on next then it will dismiss
     * @see [CoachMarkScope.show]
     */
    GoNext,

    /**
     * Event denoting that on click on overlay, it would dismiss the current active tooltip
     * and also won't show next tooltip even if it's there
     * @see [CoachMarkScope.show]
     */
    DismissAll,

    /**
     * Event denoting that there will be no effect on clicking on overlay
     * it will stay as it is
     */
    None
}
