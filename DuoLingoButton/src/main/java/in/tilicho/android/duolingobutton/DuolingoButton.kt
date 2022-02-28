package `in`.tilicho.android.duolingobutton

import `in`.tilicho.android.duolingobutton.databinding.ViewDuolingoButtonBinding
import `in`.tilicho.android.duolingobutton.interfaces.CustomButtonOnClickListener
import `in`.tilicho.android.duolingobutton.utils.DisplayConversionUtils
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible

class DuolingoButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : RelativeLayout(context, attrs, defStyle) {


    private var binding =  ViewDuolingoButtonBinding.inflate(LayoutInflater.from(context),this)
    private lateinit var onClickListener: CustomButtonOnClickListener

    private lateinit var backgroundTopDrawable: GradientDrawable

    private lateinit var backgroundBottomDrawable: GradientDrawable

    private lateinit var backgroundNormalDrawable: GradientDrawable


    var textpaddingStart: Int = 0
        set(value) {
            field = value
            binding.buttonTextLayout.setPadding(
                pixelsToDpi(value),
                binding.buttonTextLayout.paddingTop,
                binding.buttonTextLayout.paddingRight,
                binding.buttonTextLayout.paddingBottom
            )
        }

    var textpaddingEnd: Int = 0
        set(value) {
            field = value

            binding.buttonTextLayout.setPadding(
                binding.buttonTextLayout.paddingLeft,
                binding.buttonTextLayout.paddingTop,
                pixelsToDpi(value),
                binding.buttonTextLayout.paddingBottom
            )
        }

    var textpaddingTop: Int = 0
        set(value) {
            field = value
            binding.buttonTextLayout.setPadding(
                binding.buttonTextLayout.paddingLeft,
                pixelsToDpi(value),
                binding.buttonTextLayout.paddingRight,
                binding.buttonTextLayout.paddingBottom
            )
        }

    var textpaddingBottom: Int = 0
        set(value) {
            field = value
            binding.buttonTextLayout.setPadding(
                binding.buttonTextLayout.paddingLeft,
                binding.buttonTextLayout.paddingTop,
                binding.buttonTextLayout.paddingRight,
                pixelsToDpi(value)
            )
        }

    var buttonText: String = ""
        set(value) {
            field = value
            binding.buttonTextView.setText(value)
        }

    var buttonTextSize: Float = 24.toFloat()
        set(value) {
            field = value
            binding.buttonTextView.textSize =
                value
        }

    @ColorInt
    var buttonTextColor: Int = ContextCompat.getColor(context,R.color.black)//resources.getColor(R.color.black,context.theme)
        set(value) {
            field = value
            binding.buttonTextView.setTextColor(value)
        }

    var buttonTextAppearance: Int? = null
    set(value) {
        field = value
        if(value!=null){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                binding.buttonTextView.setTextAppearance(value)
            }
        }
    }

    var buttonRadius: Int = 12
        set(value) {
            field = value
            setCornerRadius(value)
        }

    @ColorInt
    var buttonPrimaryColor: Int = ContextCompat.getColor(context,R.color.colorDefaultPrimary)//R.color.colorDefaultPrimary
        set(value) {
            field = value
            setTopBackgroundColor(buttonPrimaryColor)
        }

    @ColorInt
    var buttonShadowColor: Int = ContextCompat.getColor(context,R.color.colorDefaultShadow)//R.color.colorDefaultShadow
        set(value) {
            field = value
            setBackgroundShadowColor(value)
        }

    var widthMatchParent: Boolean = false
        set(value) {
            field = value
            setButtonWidth(value)
        }

    var iconSpacer: Int = 8
        set(value) {
            field = value
            setImageSpacer(value)
        }

    @DrawableRes
    var buttonIcon: Int? = null
        set(value) {
            field = value
            if (value != null) {
                setCtaButtonIcon(value)
            } else {
                binding.buttonIcon.visibility = View.GONE
            }
        }

    private fun setCtaButtonIcon(value: Int) {
        binding.buttonIcon.visibility = View.VISIBLE
        binding.buttonIcon.setImageResource(value)
    }

    private fun setImageSpacer(value: Int) {
        val layoutParams = binding.buttonIcon.layoutParams as LinearLayout.LayoutParams
        layoutParams.marginEnd = DisplayConversionUtils.dpToPx(context, value.toFloat()).toInt()
        binding.buttonIcon.layoutParams = layoutParams
    }

    fun setButtonWidth(value: Boolean) {
        if (value) {
            val layoutParams = binding.buttonTextLayout.layoutParams as LayoutParams
            layoutParams.width = LayoutParams.MATCH_PARENT
            binding.buttonTextLayout.layoutParams = layoutParams
        }
    }


    private fun setCornerRadius(value: Int) {
        val valueInDp = DisplayConversionUtils.dpToPx(context, value.toFloat())
        backgroundBottomDrawable.cornerRadius = valueInDp
        backgroundTopDrawable.cornerRadius = valueInDp
    }

    fun setBackgroundShadowColor(color: Int) {
        backgroundBottomDrawable.setColor(ContextCompat.getColor(context, color))
        setBackgroundDrawable()
    }

    private fun setTopBackgroundColor(color: Int) {
        backgroundTopDrawable.setColor(ContextCompat.getColor(context, color))
        backgroundNormalDrawable.setColor(ContextCompat.getColor(context, color))
        setBackgroundDrawable()
    }

    private fun setBackgroundDrawable() {
        binding.buttonTextLayout.background =
            ContextCompat.getDrawable(context, R.drawable.drawable_background_with_shadow)
    }

    private fun setRelevantBackground() {
        backgroundTopDrawable.setColor(buttonPrimaryColor)
        backgroundNormalDrawable.setColor(buttonPrimaryColor)
        backgroundBottomDrawable.setColor(buttonShadowColor)
        setCornerRadius(buttonRadius)
    }

    private fun initDrawables() {
        val shape =
            ContextCompat.getDrawable(
                context,
                R.drawable.drawable_background_with_shadow
            ) as LayerDrawable

        backgroundTopDrawable = (shape.findDrawableByLayerId(R.id.top_drawable) as GradientDrawable)
        backgroundBottomDrawable =
            (shape.findDrawableByLayerId(R.id.bottom_drawable) as GradientDrawable)

        val normalDrawable = ContextCompat.getDrawable(
            context,
            R.drawable.drawable_background_without_shadow
        ) as LayerDrawable

        backgroundNormalDrawable =
            (normalDrawable.findDrawableByLayerId(R.id.top_drawable) as GradientDrawable)
    }

    fun changeDrawableColor(topColor: String, bottomColor: String, cornerRadius: Float) {
        val shape =
            ContextCompat.getDrawable(
                context,
                R.drawable.drawable_background_with_shadow
            ) as LayerDrawable

        val changedBackground = (shape.findDrawableByLayerId(R.id.top_drawable) as GradientDrawable)
        changedBackground.setColor(Color.parseColor(topColor))
        changedBackground.cornerRadius = DisplayConversionUtils.dpToPx(context, cornerRadius)

        val updatedBackground =
            (shape.findDrawableByLayerId(R.id.bottom_drawable) as GradientDrawable)
        updatedBackground.setColor(Color.parseColor(bottomColor))

        updatedBackground.cornerRadius = DisplayConversionUtils.dpToPx(context, cornerRadius)

        binding.buttonTextLayout.background =
            ContextCompat.getDrawable(context, R.drawable.drawable_background_with_shadow)

        val normalDrawable = ContextCompat.getDrawable(
            context,
            R.drawable.drawable_background_without_shadow
        ) as LayerDrawable

        val normalChangedBackground =
            (normalDrawable.findDrawableByLayerId(R.id.top_drawable) as GradientDrawable)
        normalChangedBackground.setColor(Color.parseColor(topColor))
        normalChangedBackground.cornerRadius = DisplayConversionUtils.dpToPx(context, cornerRadius)

    }


    init {
        initDrawables()
        initBackground()
        initListeners()
        parseCustomAttributes(attrs)
    }

    private fun initBackground() {
        val drawable: Drawable? = ContextCompat.getDrawable(
            context.applicationContext,
            R.drawable.drawable_background_with_shadow
        )

        binding.buttonTextLayout.background = drawable
    }


    fun setTextStartDrawable(drawable: Drawable) {
        binding.buttonTextView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
    }

    fun setDrawable() {
        binding.buttonTextLayout.background = ContextCompat.getDrawable(
            context,
            R.drawable.drawable_background_with_shadow
        )
    }

    fun isProgressBarVisible(): Boolean {
        return binding.loadingProgressBar.isVisible
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initListeners() {
        binding.buttonTextLayout.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                setRelevantBackground()
                when (event!!.action) {
                    MotionEvent.ACTION_DOWN -> {
                        binding.buttonTextLayout.background = ContextCompat.getDrawable(
                            context,
                            R.drawable.drawable_background_without_shadow
                        )//getButtonWithOutBackground(buttonColor)

                        binding.buttonTextLayout.setPadding(
                            binding.buttonTextLayout.paddingLeft,
                            binding.buttonTextLayout.paddingTop + pixelsToDpi(4),
                            binding.buttonTextLayout.paddingEnd,
                            binding.buttonTextLayout.paddingBottom - pixelsToDpi(4)
                        )
                        return true;
                    }

                    MotionEvent.ACTION_CANCEL -> {
                        binding.buttonTextLayout.background = ContextCompat.getDrawable(
                            context,
                            R.drawable.drawable_background_with_shadow
                        )//getButtonWithBackground(buttonColor)

                        binding.buttonTextLayout.setPadding(
                            binding.buttonTextLayout.paddingLeft,
                            binding.buttonTextLayout.paddingTop - pixelsToDpi(4),
                            binding.buttonTextLayout.paddingEnd,
                            binding.buttonTextLayout.paddingBottom + pixelsToDpi(4)
                        )
                    }

                    MotionEvent.ACTION_UP -> {

                        binding.buttonTextLayout.background = ContextCompat.getDrawable(
                            context,
                            R.drawable.drawable_background_with_shadow
                        )//getButtonWithBackground(buttonColor)

                        binding.buttonTextLayout.setPadding(
                            binding.buttonTextLayout.paddingLeft,
                            binding.buttonTextLayout.paddingTop - pixelsToDpi(4),
                            binding.buttonTextLayout.paddingEnd,
                            binding.buttonTextLayout.paddingBottom + pixelsToDpi(4)
                        )

                        if(::onClickListener.isInitialized) {
                            onClickListener.onClicked()
                        }
                        return false;
                    }
                }
                return false
            }
        })
    }

    fun convertSpToPixels(sp: Float, context: Context): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            sp,
            context.resources.displayMetrics
        )
    }

    fun pixelsToDpi(pixel: Int): Int {
        //final float scale = getResources().getDisplayMetrics().density;
        val scale = context.resources.displayMetrics.density
        return (pixel * scale + 0.5f).toInt()
    }

    fun pixelsToSpi(pixel: Int): Float {
        val scale = context.resources.displayMetrics.density
        return pixel / scale
    }


    private fun parseCustomAttributes(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.DuolingoButton)
        try {
            textpaddingStart = typedArray.getInt(
                R.styleable.DuolingoButton_textPaddingStart,
                binding.buttonTextLayout.paddingLeft
            )
            textpaddingEnd = typedArray.getInt(
                R.styleable.DuolingoButton_textPaddingEnd,
                binding.buttonTextLayout.paddingEnd
            )
            textpaddingTop = typedArray.getInt(
                R.styleable.DuolingoButton_textPaddingTop,
                binding.buttonTextLayout.paddingTop
            )
            textpaddingBottom = typedArray.getInt(
                R.styleable.DuolingoButton_textPaddingBottom,
                binding.buttonTextLayout.paddingBottom
            )
            buttonText =
                typedArray.getString(R.styleable.DuolingoButton_android_text).toString()

            buttonTextSize = typedArray.getFloat(
                R.styleable.DuolingoButton_textSize,
                pixelsToSpi(24)
            )

            buttonTextColor = typedArray.getColor(
                R.styleable.DuolingoButton_android_textColor,
                ContextCompat.getColor(context, R.color.black)
            )

            buttonRadius = typedArray.getInteger(
                R.styleable.DuolingoButton_buttonCornerRadius,
                12
            )

            buttonPrimaryColor = typedArray.getResourceId(
                R.styleable.DuolingoButton_primaryColor,
                R.color.colorDefaultPrimary
            )
            buttonShadowColor = typedArray.getResourceId(
                R.styleable.DuolingoButton_shadowColor,
                R.color.colorDefaultShadow
            )
            //widthMatchParent = typedArray.getBoolean(R.styleable.DuolingoButton_ctaFullButton,false)

            buttonIcon =
                typedArray.getResourceId(
                    R.styleable.DuolingoButton_icon,
                    R.drawable.drawable_background_with_shadow
                )
            iconSpacer = typedArray.getInteger(R.styleable.DuolingoButton_iconSpacer, 8)

            buttonTextAppearance = typedArray.getResourceId(R.styleable.DuolingoButton_textAppearance,R.style.TextAppearance_AppCompat)
        } finally {
            typedArray.recycle()
        }
    }

    fun registerOnClickListener(onClickListener: CustomButtonOnClickListener) {
        this.onClickListener = onClickListener
    }

    fun setButtonTitle(title: String) {
        binding.buttonTextView.setText(title)
    }


    fun getButtonTitle(): String {
        return binding.buttonTextView.text.toString()
    }

    fun showProgressBar() {
        binding.buttonTextView.visibility = INVISIBLE
        binding.loadingProgressBar.visibility = VISIBLE
    }

    fun hideProgressBar() {
        binding.buttonTextView.visibility = VISIBLE
        binding.loadingProgressBar.visibility = GONE
    }

}
